package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.EnumMapperType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeStatus implements EnumMapperType {
  IN_PROGRESS("작성중"),
  PUBLISHED("공개");

  private final String description;

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getDescription() {
    return description;
  }
}
