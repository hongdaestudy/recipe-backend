package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;

public interface RecipeRepositoryCustom {
  RetrieveRecipeCommandResult findOneByRecipeId(long recipeId);

  long deleteRecipe(long recipeId);
}
