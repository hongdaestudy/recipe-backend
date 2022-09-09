package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO jaesay: 1. 지금, 2. 레시피 엔티티 이너 클래스로 할까.., 3. 아니면 domain 하위 패이지에서 관리?
// TODO jaesay: 만개의 레시피 실제값이랑 다르게 그냥 쉬움, 보통, 어려움으로 해도 될지..
@AllArgsConstructor
@Getter
public enum RecipeDifficultyLevel {
  EASY("쉬움"),
  NORMAL("보통"),
  DIFFICULT("어려움");

  private final String description;
}
