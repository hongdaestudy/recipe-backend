package com.hongdaestudy.recipebackend.recipe.presentation;

import com.hongdaestudy.recipebackend.recipe.application.RegisterRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.RetrieveRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RegisterRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeCommandController {

  private final RegisterRecipeService registerRecipeService;
  private final RetrieveRecipeService retrieveRecipeService;

  @PostMapping("/recipes")
  public ResponseEntity<RegisterRecipeCommandResult> registerRecipe(@RequestBody RegisterRecipeCommand registerRecipeCommand) {

    RegisterRecipeCommandResult result = registerRecipeService.registerRecipe(registerRecipeCommand);

    return ResponseEntity.ok(result);
  }
  @GetMapping("/recipe")
  public ResponseEntity<RetrieveRecipeCommandResult> retrieveRecipe(@RequestBody RetrieveRecipeCommand retrieveRecipeCommand) {

    RetrieveRecipeCommandResult result = retrieveRecipeService.retrieveRecipe(retrieveRecipeCommand);

    return ResponseEntity.ok(result);
  }
}
