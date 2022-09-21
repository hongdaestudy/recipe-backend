package com.hongdaestudy.recipebackend.ingredient.domain.repository;

import com.hongdaestudy.recipebackend.ingredient.domain.IngredientGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientGroupRepository extends JpaRepository<IngredientGroup, Long> {
}
