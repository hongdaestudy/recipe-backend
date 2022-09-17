package com.hongdaestudy.recipebackend.ingredient.application.in;

import lombok.Data;

import java.util.List;

@Data
public class RegisterIngredientGroupCommand {
  private Long recipeId;
  private String name;
  private int sort;
  private List<RegisterIngredientCommand> ingredients;

  @Data
  public static class RegisterIngredientCommand {
    private String name;
    private String amount;
    private int sort;
  }
}
