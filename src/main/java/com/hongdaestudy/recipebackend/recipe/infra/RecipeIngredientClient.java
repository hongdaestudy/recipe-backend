package com.hongdaestudy.recipebackend.recipe.infra;

import com.hongdaestudy.recipebackend.common.DomainService;
import com.hongdaestudy.recipebackend.ingredient.application.RegisterIngredientGroupService;
import com.hongdaestudy.recipebackend.recipe.domain.service.RecipeIngredientService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class RecipeIngredientClient implements RecipeIngredientService {

  private final RegisterIngredientGroupService registerIngredientGroupService;

  @Override
  public void createIngredients() {}
}
