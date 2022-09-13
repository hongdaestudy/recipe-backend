package com.hongdaestudy.recipebackend.recipe.repository;

import com.hongdaestudy.recipebackend.recipe.domain.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
