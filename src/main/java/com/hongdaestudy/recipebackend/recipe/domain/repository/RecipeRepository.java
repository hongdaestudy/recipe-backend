package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeRepositoryCustom {
  List<Recipe> findByDeleteAt(char deleteAt);
}