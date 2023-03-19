package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeKind {
  SIDE("밑반찬"),
  MAIN("메인반찬"),
  GUG_TANG("국/탕"),
  STEW("찌개"),
  DESSERT("디저트"),
  NOODLE("면/만두"),
  RICE("밥/죽/떡"),
  FUSION("퓨전"),
  PASTE("김치/젓갈/장류"),
  SAUCE("양념/소스/잼"),
  WESTERN("양식"),
  SALAD("샐러드"),
  SOUP("스프"),
  BREAD("빵"),
  SNACK("과자"),
  DRINK("차/음료/술"),
  OTHERS("기타");

  private final String description;
}
