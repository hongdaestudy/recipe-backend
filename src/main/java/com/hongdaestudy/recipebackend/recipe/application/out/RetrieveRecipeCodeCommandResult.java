package com.hongdaestudy.recipebackend.recipe.application.out;

import com.hongdaestudy.recipebackend.common.EnumMapperValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RetrieveRecipeCodeCommandResult {
  private List<EnumMapperValue> status;

  private List<EnumMapperValue> cookingTime;
  private List<EnumMapperValue> difficultyLevel;
  private List<EnumMapperValue> servingCount;

  private List<EnumMapperValue> kind;
  private List<EnumMapperValue> method;
  private List<EnumMapperValue> situation;
  private List<EnumMapperValue> ingredient;

  public static RetrieveRecipeCodeCommandResult create( List<EnumMapperValue> status,
          List<EnumMapperValue> cookingTime, List<EnumMapperValue> difficultyLevel, List<EnumMapperValue> servingCount,
          List<EnumMapperValue> kind, List<EnumMapperValue> method, List<EnumMapperValue> situation,List<EnumMapperValue> ingredient) {
    RetrieveRecipeCodeCommandResult recipeCode =new RetrieveRecipeCodeCommandResult();

    recipeCode.status = status;
    recipeCode.cookingTime = cookingTime;
    recipeCode.difficultyLevel = difficultyLevel;
    recipeCode.servingCount = servingCount;

    recipeCode.kind = kind;
    recipeCode.method = method;
    recipeCode.situation = situation;
    recipeCode.ingredient = ingredient;


    return recipeCode;
  }


}
