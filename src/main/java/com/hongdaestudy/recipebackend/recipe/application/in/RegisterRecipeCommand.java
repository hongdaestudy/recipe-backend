package com.hongdaestudy.recipebackend.recipe.application.in;

import com.hongdaestudy.recipebackend.ingredient.application.in.RegisterIngredientGroupCommand;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeCookingTime;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeDifficultyLevel;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeServingCount;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRecipeCommand {
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
  private List<RegisterIngredientGroupCommand> ingredientGroups;

  @Data
  public static class RegisterRecipeStepCommand {
    private String description;
    private Long photoFileId;
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
