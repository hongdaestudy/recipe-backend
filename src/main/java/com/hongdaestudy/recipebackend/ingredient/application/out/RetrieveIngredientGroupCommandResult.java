package com.hongdaestudy.recipebackend.ingredient.application.out;

import lombok.Data;

import java.util.List;

@Data
public class RetrieveIngredientGroupCommandResult {
  private String name;
  private int sort;
  private List<RetrieveIngredientCommandResult> ingredients;

  @Data
  public static class RetrieveIngredientCommandResult {
    private String name;
    private String amount;
    private int sort;

    public static RetrieveIngredientCommandResult create(String name, String amount, int sort) {
      RetrieveIngredientCommandResult retrieveIngredientCommandResult = new RetrieveIngredientCommandResult();
      retrieveIngredientCommandResult.name = name;
      retrieveIngredientCommandResult.amount = amount;
      retrieveIngredientCommandResult.sort = sort;
      return retrieveIngredientCommandResult;
    }
  }
  public static RetrieveIngredientGroupCommandResult create(String name, int sort, List<RetrieveIngredientCommandResult> ingredients) {
    RetrieveIngredientGroupCommandResult retrieveIngredientGroupCommandResult = new RetrieveIngredientGroupCommandResult();
    retrieveIngredientGroupCommandResult.name = name;
    retrieveIngredientGroupCommandResult.sort = sort;
    retrieveIngredientGroupCommandResult.ingredients = ingredients;
    return retrieveIngredientGroupCommandResult;
  }
}
