package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeIngredient {
  BEEF("소고기"),
  PORK("돼지고기"),
  CHICKEN("닭고기"),
  MEAT("육류"),
  VEGETABLES("채소류"),
  SEAFOOD("해물류"),
  EGG_DAILY("달걀/유제품"),
  PROCESSED("가공식품류"),
  RICE("쌀"),
  FLOUR("밀가루"),
  DRIED("건어물류"),
  MUSHROOMS("버섯류"),
  FRUITS("과일류"),
  BEANS_NUTS("콩/견과류"),
  CEREALS("곡류"),
  OTHERS("기타");

  private final String description;
}
