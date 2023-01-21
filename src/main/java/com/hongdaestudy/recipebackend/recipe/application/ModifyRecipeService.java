package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyRecipeService {

	private final RecipeRepository recipeRepository;

	@Transactional
	public Long deleteRecipe(Long recipeId) throws Exception {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		entity.delete();
		return recipeId;
	}

	@Transactional
	public Long updateRecipe(long recipeId, RegisterRecipeCommand params) throws Exception {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		// 일단 completionPhotoFileId는 null로
		entity.updateRecipeInfo(null, params.getDescription(), params.getMainPhotoFileId(),
								params.getStatus(), params.getTip(), params.getTitle(), params.getVideoUrl());
		return recipeId;
	}

}
