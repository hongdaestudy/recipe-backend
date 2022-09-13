package com.hongdaestudy.recipebackend.recipe.application.in;

import com.hongdaestudy.recipebackend.recipe.domain.RecipeCookingTime;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeDifficultyLevel;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus;
import lombok.Data;

import java.util.List;

@Data
public class RecipeCommand {
  private final Long memberId;
  private final List<RecipeStepCommand> recipeSteps;
  private final String title;
  private final String description;
  private final String videoUrl;
  private final RecipeInformationCommand information;
  private final String completionPhotoUrl;
  private final String tip;
  private final List<RecipeTagCommand> recipeTags;
  private final RecipeStatus status;

  @Data
  public static class RecipeStepCommand {
    private final String description;
    private final String photoUrl;
    private final int sortOrder;
  }

  @Data
  public static class RecipeInformationCommand {
    private final RecipeDifficultyLevel servingCount;
    private final RecipeCookingTime cookingTime;
    private final RecipeDifficultyLevel difficultyLevel;
  }

  @Data
  public static class RecipeTagCommand {
    private final String name;
    private final int sortOrder;
  }
}
