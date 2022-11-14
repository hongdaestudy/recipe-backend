package com.hongdaestudy.recipebackend.scrap.application.out;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteScrapCommandResult {
  private Long recipeId;
}