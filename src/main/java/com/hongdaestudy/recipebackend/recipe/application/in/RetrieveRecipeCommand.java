package com.hongdaestudy.recipebackend.recipe.application.in;

import com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus;
import lombok.Data;

@Data
public class RetrieveRecipeCommand {
  private Long recipeId;
  private Long memberId;
  private String title;
  private RecipeStatus status;
}
