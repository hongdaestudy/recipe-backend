package com.hongdaestudy.recipebackend.recipe.application.in;

import com.hongdaestudy.recipebackend.recipe.domain.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchRecipeCommand {
  private Long recipeId;
  private Long memberId;

  private RecipeKind kind;
  private RecipeSituation situation;
  private RecipeMethod method;
  private RecipeIngredient ingredient;

  private RecipeServingCount servingCount;
  private RecipeCookingTime cookingTime;
  private RecipeDifficultyLevel difficultyLevel;

  private RecipeStatus status;

  private String tip;

  private String title;
  private List<RecipeTag> recipeTags = new ArrayList<>();
}
