package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;

import java.util.List;

public interface RecipeRepositoryCustom {
  RetrieveRecipeCommandResult findOneByRecipeId(long recipeId);
  List<RetrieveRecipeCommandResult> findAllNotDeletedRecipesById(RetrieveRecipeCommand retrieveRecipeCommand);
  void updateView(long recipeId);
}
