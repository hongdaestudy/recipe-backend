package com.hongdaestudy.recipebackend.recipe.application.mapper;

import com.hongdaestudy.recipebackend.recipe.application.in.RecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeEntity;
import org.springframework.stereotype.Component;

@Component
// TODO jaesay: 맵퍼에 둘지...아니면 서비스 단이나..각각 클래스 안에 둘지..등등 고민
public class RecipeMapper {
  public RecipeEntity toEntity(RecipeCommand command) {
    return RecipeEntity.create();
  }

  public RecipeCommandResult toCommandResult(RecipeEntity entity) {
    return new RecipeCommandResult();
  }
}
