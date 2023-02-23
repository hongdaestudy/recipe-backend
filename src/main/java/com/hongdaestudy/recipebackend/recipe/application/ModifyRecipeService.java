package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.config.error.exception.CustomException;
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
	public Long deleteRecipe(Long recipeId) {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		entity.delete();
		return recipeId;
	}

	@Transactional
	public Long updateRecipe(RegisterRecipeCommand params) {
		Recipe entity = recipeRepository.findById(params.getRecipeId()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
		entity.updateRecipeInfo(params);
		return params.getRecipeId();
	}
}
