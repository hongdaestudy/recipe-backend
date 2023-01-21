package com.hongdaestudy.recipebackend.recipe.application.out;

import com.hongdaestudy.recipebackend.ingredient.application.out.RetrieveIngredientGroupCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeCookingTime;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeDifficultyLevel;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeServingCount;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Data
public class RetrieveRecipeCommandResult {
  private Long id;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Long completionPhotoFileId;
  private String description;
  private RetrieveRecipeInformationCommandResult information;
  private Long mainPhotoFileId;
  private Long memberId;
  private RecipeStatus status;
  private String tip;
  private String title;
  private String videoUrl;
  private char deleteAt;

  private List<RetrieveRecipeStepCommandResult> recipeSteps;
  private List<RetrieveRecipeTagCommandResult> recipeTags;
  private List<RetrieveIngredientGroupCommandResult> ingredientGroups;

  public void addRecipeStep(RetrieveRecipeStepCommandResult recipeStep) {
    this.recipeSteps.add(recipeStep);
  }

  public void addRecipeTag(RetrieveRecipeTagCommandResult recipeTag) {
    this.recipeTags.add(recipeTag);
  }

  public void addIngredientGroup(RetrieveIngredientGroupCommandResult ingredientGroup) {
    this.ingredientGroups.add(ingredientGroup);
  }

  @Data
  public static class RetrieveRecipeStepCommandResult {
    private String description;
    private Long photoFileId;
    private int sort;

    @QueryProjection
    public RetrieveRecipeStepCommandResult(String description, Long photoFileId, int sort) {
      this.description = description;
      this.photoFileId = photoFileId;
      this.sort = sort;
    }
  }

  @Data
  public static class RetrieveRecipeInformationCommandResult {
    private RecipeServingCount servingCount;
    private RecipeCookingTime cookingTime;
    private RecipeDifficultyLevel difficultyLevel;

    public static RetrieveRecipeInformationCommandResult create(RecipeServingCount servingCount, RecipeCookingTime cookingTime, RecipeDifficultyLevel difficultyLevel) {
      RetrieveRecipeInformationCommandResult information = new RetrieveRecipeInformationCommandResult();
      information.servingCount = servingCount;
      information.cookingTime = cookingTime;
      information.difficultyLevel = difficultyLevel;
      return information;
    }
  }

  @Data
  public static class RetrieveRecipeTagCommandResult {
    private String name;
    private int sort;

    @QueryProjection
    public RetrieveRecipeTagCommandResult(String name, int sort) {
      this.name = name;
      this.sort = sort;
    }
  }

  @QueryProjection
  public RetrieveRecipeCommandResult(
          Long id,
          LocalDateTime createdAt,
          LocalDateTime updatedAt,
          Long memberId,
          //List<RetrieveRecipeStepCommandResult> recipeSteps,
          String title,
          String description,
          String videoUrl,
          RecipeServingCount servingCount,
          RecipeCookingTime cookingTime,
          RecipeDifficultyLevel difficultyLevel,
          Long mainPhotoFileId,
          Long completionPhotoFileId,
          String tip,
          //List<RetrieveRecipeTagCommandResult> recipeTags,
          RecipeStatus status,
          char deleteAt) {

    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.memberId = memberId;
    this.title = title;
    this.description = description;
    this.videoUrl = videoUrl;
    this.information = RetrieveRecipeInformationCommandResult.create(servingCount, cookingTime, difficultyLevel);
    this.mainPhotoFileId = mainPhotoFileId;
    this.completionPhotoFileId = completionPhotoFileId;
    this.tip = tip;
    this.status = status;
    this.deleteAt = deleteAt;

    this.recipeSteps = new ArrayList<>();
    this.recipeTags = new ArrayList<>();
    this.ingredientGroups = new ArrayList<>();
  }
}
