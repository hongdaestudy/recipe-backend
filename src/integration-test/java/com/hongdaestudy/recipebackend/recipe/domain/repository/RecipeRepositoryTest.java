package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("RecipeRepository 테스트")
class RecipeRepositoryTest {

  @Autowired
  private RecipeRepository recipeRepository;

  @Nested
  @DisplayName("save 메소드는")
  class Describe_save {

    @BeforeEach
    void setUp() {
      recipeRepository.deleteAll();
    }

    @Nested
    @DisplayName("레시피 객체가 주어지면")
    class Context_with_a_recipe {
      private final Recipe recipe = Recipe.create(
          1L,
          "타이틀명",
          "설명",
          1L,
          RecipeInformation.create(RecipeServingCount.ONE, RecipeCookingTime.FIFTEEN_MINUTES_LESS, RecipeDifficultyLevel.EASY),
          1L,
          "팁",
          List.of(
              RecipeStep.create("설명1", null, 1),
              RecipeStep.create("설명2", null, 2)
          ),
          List.of(
              RecipeTag.create("태그명1", 1),
              RecipeTag.create("태그명2", 2)
          ),
          RecipeStatus.IN_PROGRESS);

      @Test
      @DisplayName("주어진 레시피를 저장하고, 저장된 레시피를 리턴한다.")
      void it_saves_a_recipe_and_returns_a_saved_recipe() {
        Recipe savedRecipe = recipeRepository.save(recipe);
        assertThat(savedRecipe).satisfies(actual -> {
          assertThat(actual).as("저장된 레시피는 null이 아니다.").isNotNull();
          assertThat(actual.getId()).as("저장된 레시피의 ID는 0보다 큰 값으로 저장된다.").isGreaterThan(0);
        });
      }
    }
  }
}