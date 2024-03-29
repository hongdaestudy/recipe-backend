package com.hongdaestudy.recipebackend.recipe.application.in;

import com.hongdaestudy.recipebackend.ingredient.application.in.RegisterIngredientGroupCommand;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRecipeCommand {
  private Long memberId;
  private List<RegisterRecipeStepCommand> recipeSteps;
  private String title;
  private String description;
  private String videoUrl;
  private RegisterRecipeCategoryCommand category;
  private RegisterRecipeInformationCommand information;
  private Long mainPhotoFileId;
  private List<Long> completionPhotoFileId; //TODO 추후 변경
  private String tip;
  private List<RegisterRecipeTagCommand> recipeTags;
  private RecipeStatus status;
  private List<RegisterIngredientGroupCommand> ingredientGroups;
  private Long recipeId;
  private Long TempCompletionPhotoFileId;

  @Data
  public static class RegisterRecipeStepCommand {
    private String description;
    private Long photoFileId;
    private int sort;
  }
  @Data
  public static class RegisterRecipeCategoryCommand {
    private RecipeKind kind;
    private RecipeSituation situation;
    private RecipeMethod method;
    private RecipeIngredient ingredient;
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
