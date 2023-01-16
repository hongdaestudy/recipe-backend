package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyRecipeService {

    private final RecipeRepository recipeRepository;

    @Transactional
    public long deleteRecipe(long recipeId) {
        return recipeRepository.deleteRecipe(recipeId);
    }
}
