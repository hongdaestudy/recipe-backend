package com.hongdaestudy.recipebackend.recipe.presentation;

import com.hongdaestudy.recipebackend.recipe.application.RegisterRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RecipeCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeCommandController {

  private final RegisterRecipeService registerRecipeService;

  @PostMapping("/recipes")
  public ResponseEntity<RecipeCommandResult> registerRecipe(@RequestBody RegisterRecipeCommand registerRecipeCommand) {

    RecipeCommandResult result = registerRecipeService.registerRecipe(registerRecipeCommand);

    return ResponseEntity.ok(result);
  }
}
