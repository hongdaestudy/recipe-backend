package com.hongdaestudy.recipebackend.recipe.infra;

import com.hongdaestudy.recipebackend.common.DomainService;
import com.hongdaestudy.recipebackend.ingredient.application.IngredientService;
import com.hongdaestudy.recipebackend.recipe.domain.service.RecipeIngredientService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RecipeIngredientClient implements RecipeIngredientService {

    private final IngredientService ingredientService;

    @Override
    public void createIngredients() {

    }
}
