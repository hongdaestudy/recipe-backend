package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeSituation {
  ROUTINE("일상"),
  SPEED("초스피드"),
  SERVE("손님접대"),
  SIDE("술안주"),
  DIET("다이어트"),
  BOXED("도시락"),
  NUTRITIONAL("영양식"),
  SNACK("간식"),
  NIGHT("야식"),
  STYLING("푸드스타일링"),
  HANGOVER("해장"),
  HOLIDAY("명절"),
  BABY("이유식"),
  OTHERS("기타");

  private final String description;
}
