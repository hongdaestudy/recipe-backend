package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.config.error.exception.CustomException;
import com.hongdaestudy.recipebackend.ingredient.application.in.RegisterIngredientGroupCommand;
import com.hongdaestudy.recipebackend.ingredient.domain.repository.IngredientGroupRepository;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeIndex;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModifyRecipeService {

	private final RecipeRepository recipeRepository;
	private final IngredientGroupRepository ingredientGroupRepository;

	private final ElasticsearchOperations operations;
	@Transactional
	public Long deleteRecipe(Long recipeId) {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		entity.delete();

		deleteRecipeIndex(recipeId);
		return recipeId;
	}

	@Transactional
	public Long updateRecipe(long recipeId, RegisterRecipeCommand params) {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

		entity.updateRecipeInfo(params.getCompletionPhotoFileId(), params.getDescription(), params.getMainPhotoFileId(),
								params.getStatus(), params.getTip(), params.getTitle(), params.getVideoUrl());

		//TODO ingredientGroup 업데이트 추가
		List<RegisterIngredientGroupCommand> ingredientGroup = params.getIngredientGroups();

		updateRecipeIndex(entity,ingredientGroup);
		return recipeId;
	}
	@Transactional
	public void updateRecipeIndex(Recipe entity, List<RegisterIngredientGroupCommand> ingredientGroup) {
		List<String> ingredients = ingredientGroup.stream().map(group -> group.getIngredients())
				.flatMap(List::stream).map(x -> x.getName()).distinct()
				.collect(Collectors.toList());
		operations.save(RecipeIndex.create(entity,ingredients));
	}
	@Transactional
	public void deleteRecipeIndex(Long recipeId) {
		operations.delete(String.valueOf(recipeId),RecipeIndex.class);
	}

}
