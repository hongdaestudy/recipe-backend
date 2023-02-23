package com.hongdaestudy.recipebackend.recipe.presentation;

import com.hongdaestudy.recipebackend.file.application.RegisterFileService;
import com.hongdaestudy.recipebackend.recipe.application.ModifyRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.RegisterRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.RetrieveRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RegisterRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeCommandController {

  private final RegisterRecipeService registerRecipeService;
  private final RetrieveRecipeService retrieveRecipeService;

  private final RegisterFileService registerFileService;
  private final ModifyRecipeService modifyRecipeService;

  // 레시피 등록
  @PostMapping("/recipes")
  public ResponseEntity<RegisterRecipeCommandResult> registerRecipe(@RequestBody RegisterRecipeCommand registerRecipeCommand) {

    RegisterRecipeCommandResult result = registerRecipeService.registerRecipe(registerRecipeCommand);
    return ResponseEntity.ok(result);
  }

  // 레시피 조회
  @GetMapping("/recipe")
  public ResponseEntity<RetrieveRecipeCommandResult> retrieveRecipe(@RequestBody RetrieveRecipeCommand retrieveRecipeCommand) {

    RetrieveRecipeCommandResult result = retrieveRecipeService.retrieveRecipe(retrieveRecipeCommand);

    return ResponseEntity.ok(result);
  }

  // 레시피 리스트 조회
  @GetMapping("/recipes")
  public ResponseEntity<List<RetrieveRecipeCommandResult>> retrieveRecipeList() {

    List<RetrieveRecipeCommandResult> result = retrieveRecipeService.retrieveRecipeList('N');

    return ResponseEntity.ok(result);
  }

  // 레시피 삭제
  @DeleteMapping("/recipe")
  public ResponseEntity<Long> deleteRecipe(@RequestBody RetrieveRecipeCommand retrieveRecipeCommand) {
    return ResponseEntity.ok(modifyRecipeService.deleteRecipe(retrieveRecipeCommand.getRecipeId()));
  }

  // 레시피 수정
  @PatchMapping("/recipe")
  public ResponseEntity<Long> updateRecipe(@RequestBody RegisterRecipeCommand registerRecipeCommand) {
    return ResponseEntity.ok(modifyRecipeService.updateRecipe(registerRecipeCommand));
  }
}
