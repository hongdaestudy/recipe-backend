package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeRepositoryCustom {

    @Modifying
    @Query(value = "update Recipe set delete_at = 'Y' where recipe_id = :recipeId", nativeQuery = true)
    void deleteRecipe(@Param("recipeId") Long recipeId);
}
