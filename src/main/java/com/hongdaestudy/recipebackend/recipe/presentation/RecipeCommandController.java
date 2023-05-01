package com.hongdaestudy.recipebackend.recipe.presentation;

import com.hongdaestudy.recipebackend.file.application.RegisterFileService;
import com.hongdaestudy.recipebackend.recipe.application.ModifyRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.RegisterRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.RetrieveRecipeService;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.SearchRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RegisterRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCodeCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeIndex;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class RecipeCommandController {

  private final RegisterRecipeService registerRecipeService;
  private final RetrieveRecipeService retrieveRecipeService;

  private final RegisterFileService registerFileService;
  private final ModifyRecipeService modifyRecipeService;

  @PostMapping("/recipe")
  public ResponseEntity<RegisterRecipeCommandResult> registerRecipe( @RequestPart("mainPhotoFile") MultipartFile mainPhotoFile
          , @RequestPart("completionPhotoFileList") MultipartFile[] completionPhotoFileList
          , @RequestPart("stepPhotoFileList") MultipartFile[] stepPhotoFileList
          , @RequestPart("recipe") RegisterRecipeCommand registerRecipeCommand) throws Exception {
    registerRecipeCommand.setMainPhotoFileId(registerFileService.uploadFile(mainPhotoFile));
    registerRecipeCommand.setCompletionPhotoFileId(registerFileService.uploadFiles(completionPhotoFileList));
    List<Long> stepFileList = registerFileService.uploadFiles(stepPhotoFileList);

    IntStream.range(0, registerRecipeCommand.getRecipeSteps().size())
            .forEach(index -> registerRecipeCommand.getRecipeSteps().get(index).setPhotoFileId(stepFileList.get(index)));

    RegisterRecipeCommandResult result = registerRecipeService.registerRecipe(registerRecipeCommand);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/recipe")
  public ResponseEntity<RetrieveRecipeCommandResult> retrieveRecipe(@RequestBody RetrieveRecipeCommand retrieveRecipeCommand) {

    RetrieveRecipeCommandResult result = retrieveRecipeService.retrieveRecipe(retrieveRecipeCommand);

    return ResponseEntity.ok(result);
  }
  @GetMapping("/recipeCode")
  public ResponseEntity<RetrieveRecipeCodeCommandResult> retrieveRecipeCode() {
    RetrieveRecipeCodeCommandResult result = retrieveRecipeService.retrieveRecipeCode();

    return ResponseEntity.ok(result);
  }
  @GetMapping("/recipes")
  public ResponseEntity<List<RetrieveRecipeCommandResult>> retrieveRecipeList(@RequestBody RetrieveRecipeCommand retrieveRecipeCommand) {

    List<RetrieveRecipeCommandResult> result = retrieveRecipeService.retrieveRecipeList(retrieveRecipeCommand);

    return ResponseEntity.ok(result);
  }

  // 삭제
  @DeleteMapping("/recipe/{id}")
  public ResponseEntity<Long> deleteRecipe(@PathVariable final Long id) {
    return ResponseEntity.ok(modifyRecipeService.deleteRecipe(id));
  }

  // 수정
  @PatchMapping("/recipe/{id}")
  public ResponseEntity<Long> updateRecipe(@PathVariable final Long id, @RequestBody final RegisterRecipeCommand params) {
    return ResponseEntity.ok(modifyRecipeService.updateRecipe(params));
  }

  @GetMapping("/search/recipes")
  public ResponseEntity<List<RecipeIndex>> retrieveRecipeByCondition(@RequestBody SearchRecipeCommand searchCondition,Pageable pageable) {
    if(pageable == null) {
      pageable = PageRequest.of(0, 50);
    }
    List<RecipeIndex> result = retrieveRecipeService.retrieveRecipeByCondition(searchCondition,pageable);

    return ResponseEntity.ok(result);
  }
}
