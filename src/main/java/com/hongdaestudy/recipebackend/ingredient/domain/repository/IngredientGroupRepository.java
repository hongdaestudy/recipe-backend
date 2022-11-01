package com.hongdaestudy.recipebackend.ingredient.domain.repository;

import com.hongdaestudy.recipebackend.ingredient.domain.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientGroupRepository extends JpaRepository<IngredientGroup, Long> {
  List<IngredientGroup> findByRecipeIdOrderBySortAsc(long recipeId);
}
