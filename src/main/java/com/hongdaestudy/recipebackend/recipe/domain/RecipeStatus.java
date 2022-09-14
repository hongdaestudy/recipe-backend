package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeStatus {
  IN_PROGRESS("작성중"),
  PUBLISHED("공개");

  private final String description;
}
