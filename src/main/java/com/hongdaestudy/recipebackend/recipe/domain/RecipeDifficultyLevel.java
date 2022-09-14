package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeDifficultyLevel {
  EASY("쉬움"),
  NORMAL("보통"),
  DIFFICULT("어려움");

  private final String description;
}
