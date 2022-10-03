package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
@ToString
public class RecipeInformation {
  @Enumerated(EnumType.STRING)
  private RecipeServingCount servingCount;

  @Enumerated(EnumType.STRING)
  private RecipeCookingTime cookingTime;

  @Enumerated(EnumType.STRING)
  private RecipeDifficultyLevel difficultyLevel;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RecipeInformation that = (RecipeInformation) o;
    return servingCount != null && Objects.equals(servingCount, that.servingCount)
        && cookingTime != null && Objects.equals(cookingTime, that.cookingTime)
        && difficultyLevel != null && Objects.equals(difficultyLevel, that.difficultyLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(servingCount, cookingTime, difficultyLevel);
  }

  public static RecipeInformation create(RecipeServingCount servingCount, RecipeCookingTime cookingTime, RecipeDifficultyLevel difficultyLevel) {
    RecipeInformation recipeInformation = new RecipeInformation();
    recipeInformation.servingCount = servingCount;
    recipeInformation.cookingTime = cookingTime;
    recipeInformation.difficultyLevel = difficultyLevel;

    return recipeInformation;
  }
}
