package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.EnumMapperType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeMethod implements EnumMapperType {
  STIR_FRIED("볶음"),
  BOIL("끓이기"),
  SERVE("부침"),
  BOILED("조림"),
  SALTED("무침"),
  MIX("비빔"),
  STEAMED("찜"),
  PICKLED("절임"),
  FRIED("튀김"),
  SIMMER("삶기"),
  BAKE("굽기"),
  BLANCH("데치기"),
  SASHIMI("회"),
  OTHERS("기타");

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
