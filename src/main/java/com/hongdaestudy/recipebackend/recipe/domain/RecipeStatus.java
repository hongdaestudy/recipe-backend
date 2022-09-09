package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO jaesay: 1. 지금, 2. 레시피 엔티티 이너 클래스로 할까.., 3. 아니면 domain 하위 패이지에서 관리?
@AllArgsConstructor
@Getter
public enum RecipeStatus {
  IN_PROGRESS("작성중"),
  PUBLISHED("공개");

  private final String description;
}
