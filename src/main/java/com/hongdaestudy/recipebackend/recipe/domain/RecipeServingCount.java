package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.EnumMapperType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeServingCount implements EnumMapperType {
  ONE("1인분"),
  TWO("2인분"),
  THREE("3인분"),
  FOUR("4인분"),
  FIVE("5인분"),
  SIX("6인분"),
  SIX_MORE("6인분 이상");

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
