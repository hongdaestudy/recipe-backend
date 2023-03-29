package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;

public interface RecipeRepositoryCustom {
  RetrieveRecipeCommandResult findOneByRecipeId(long recipeId);
}
