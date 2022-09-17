package com.hongdaestudy.recipebackend.recipe.presentation;

import com.hongdaestudy.recipebackend.recipe.application.RegisterRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeCommandController {

  private final RegisterRecipeService registerRecipeService;

//  @PostMapping("/recipes")
//  public ResponseEntity<RecipeCommandResult> registerRecipe(RecipeCommand command) {
//    RecipeCommandResult result = registerRecipeService.createRecipe(command);
//    return ResponseEntity.ok(result);
//  }
}
