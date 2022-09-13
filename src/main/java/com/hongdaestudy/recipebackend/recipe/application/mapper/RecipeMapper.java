package com.hongdaestudy.recipebackend.recipe.application.mapper;

import com.hongdaestudy.recipebackend.recipe.application.in.RecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeEntity;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    public RecipeEntity toEntity(RecipeCommand command) {
        return RecipeEntity.create();
    }

    public RecipeCommandResult toCommandResult(RecipeEntity entity) {
        return new RecipeCommandResult();
    }
}
