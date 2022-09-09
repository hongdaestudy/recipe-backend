package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO jaesay: 1. 지금, 2. 레시피 엔티티 이너 클래스로 할까.., 3. 아니면 domain 하위 패이지에서 관리?
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
    TWO_HOUR_LESS("2시간 이내"),
    TWO_HOUR_MORE("2시간 이후");

    private final String description;
}
