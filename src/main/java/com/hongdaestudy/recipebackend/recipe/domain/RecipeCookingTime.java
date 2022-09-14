package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RecipeCookingTime {
  FIVE_MINUTES_LESS("5분 이내"),
  TEN_MINUTES_LESS("10분 이내"),
  FIFTEEN_MINUTES_LESS("15분 이내"),
  TWENTY_MINUTES_LESS("20분 이내"),
  THIRTY_MINUTES_LESS("30분 이내"),
  SIXTY_MINUTES_LESS("60분 이내"),
  NINETY_MINUTES_LESS("90분 이내"),
  TWO_HOURS_LESS("2시간 이내"),
  TWO_HOURS_MORE("2시간 이후");

  private final String description;
}
