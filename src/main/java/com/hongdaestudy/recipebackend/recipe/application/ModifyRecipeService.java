package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.config.error.ErrorResponse;
import com.hongdaestudy.recipebackend.config.error.exception.DuplicationException;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeRequestDto;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModifyRecipeService {

    private final RecipeRepository recipeRepository;

    @Transactional
    public Long deleteRecipe(long recipeId) throws Exception {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		entity.delete();
        return recipeId;
    }

	@Transactional
	public Long updateRecipe(long recipeId, RecipeRequestDto params) throws Exception {
		Recipe entity = recipeRepository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		entity.updateRecipeInfo(params.getTitle(), params.getDescription(), params.getVideoUrl(),
								params.getCompletionPhotoFileId(), params.getTip(), params.getDeleteAt());
		return recipeId;
	}
}
