package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeRepositoryCustom {
  RetrieveRecipeCommandResult findOneByRecipeId(long recipeId);
  Page<RetrieveRecipeCommandResult> findAllNotDeletedRecipesById(RetrieveRecipeCommand retrieveRecipeCommand, Pageable pageable);
}
