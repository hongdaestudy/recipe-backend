package com.hongdaestudy.recipebackend.recipe.application.in;

import lombok.Data;

@Data
public class RetrieveRecipeCommand {
  private Long recipeId;
  private Long memberId;

}
