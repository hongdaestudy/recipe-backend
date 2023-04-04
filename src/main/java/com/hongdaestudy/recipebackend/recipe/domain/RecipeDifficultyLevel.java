package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.EnumMapperType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeDifficultyLevel implements EnumMapperType {
  EASY("쉬움"),
  NORMAL("보통"),
  DIFFICULT("어려움");

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
