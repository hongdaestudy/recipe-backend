package com.hongdaestudy.recipebackend.recipe.application.in;

import com.hongdaestudy.recipebackend.recipe.domain.RecipeCookingTime;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeDifficultyLevel;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeServingCount;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRegisterRecipeCommand {
  private Long memberId;
  private List<RegisterRecipeStepCommand> recipeSteps;
  private String title;
  private String description;
  private Long videoFileId;
  private RegisterRecipeInformationCommand information;
  private Long completionPhotoFileId;
  private String tip;
  private List<RegisterRecipeTagCommand> recipeTags;
  private RecipeStatus status;

  @Data
  public static class RegisterRecipeStepCommand {
    private String description;
    private String photoUrl;
    private int sort;
  }

  @Data
  public static class RegisterRecipeInformationCommand {
    private RecipeServingCount servingCount;
    private RecipeCookingTime cookingTime;
    private RecipeDifficultyLevel difficultyLevel;
  }

  @Data
  public static class RegisterRecipeTagCommand {
    private String name;
    private int sort;
  }
}
