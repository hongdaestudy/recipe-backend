package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO jaesay: 1. 지금, 2. 레시피 엔티티 이너 클래스로 할까.., 3. 아니면 domain 하위 패이지에서 관리?
@AllArgsConstructor
@Getter
public enum RecipeServingCount {
    ONE("1인분"),
    TWO("2인분"),
    THREE("3인분"),
    FOUR("4인분"),
    FIVE("5인분"),
    SIX("6인분"),
    SIX_MORE("6인분 이상");

    private final String description;
}
