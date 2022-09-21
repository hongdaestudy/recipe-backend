package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.ingredient.application.RegisterIngredientGroupService;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RegisterRecipeService 클래스")
class RegisterRecipeServiceTest {

  private final RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
  private final RegisterIngredientGroupService registerIngredientGroupService = Mockito.mock(RegisterIngredientGroupService.class);
  private final RegisterRecipeService registerRecipeService = new RegisterRecipeService(
      recipeRepository, registerIngredientGroupService
  );

  @Nested
  @DisplayName("registerRecipe 메소드는")
  class Describe_registerRecipe {

    @Test
    @DisplayName("레시피와 재료그룹을 저장하고 저장정보 일부를 리턴한다.")
    void it_saves_a_recipe_with_ingredient_groups_returns_saved_info() {

    }

  }
}