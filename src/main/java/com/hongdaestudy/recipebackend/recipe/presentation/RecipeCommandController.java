package com.hongdaestudy.recipebackend.recipe.presentation;

@RestController
@RequiredArgsConstructor
public class RecipeCommandController {

  private final RegisterRecipeService registerRecipeService;
  private final RetrieveRecipeService retrieveRecipeService;
  private final ModifyRecipeService modifyRecipeService;

  private final RegisterFileService registerFileService;

  @PostMapping("/recipes")
  public ResponseEntity<RegisterRecipeCommandResult> registerRecipe(
      @RequestPart("mainPhotoFile") MultipartFile mainPhotoFile
      , @RequestPart("completionPhotoFileList") MultipartFile[] completionPhotoFileList
      , @RequestPart("stepPhotoFileList") MultipartFile[] stepPhotoFileList
      , @RequestPart("recipe") RegisterRecipeCommand registerRecipeCommand) {

    try {
      registerRecipeCommand.setMainPhotoFileId(registerFileService.uploadFile(mainPhotoFile));
      registerRecipeCommand.setCompletionPhotoFileId(registerFileService.uploadFiles(completionPhotoFileList));
      List<Long> stepFileList = registerFileService.uploadFiles(stepPhotoFileList);

      IntStream.range(0, registerRecipeCommand.getRecipeSteps().size())
          .forEach(index -> registerRecipeCommand.getRecipeSteps().get(index).setPhotoFileId(stepFileList.get(index)));

    } catch (Exception e) {
      e.printStackTrace();
    }
    RegisterRecipeCommandResult result = registerRecipeService.registerRecipe(registerRecipeCommand);

    return ResponseEntity.ok(result);
  }
  @GetMapping("/recipe")
  public ResponseEntity<RetrieveRecipeCommandResult> retrieveRecipe(@RequestBody RetrieveRecipeCommand retrieveRecipeCommand) {

    RetrieveRecipeCommandResult result = retrieveRecipeService.retrieveRecipe(retrieveRecipeCommand);

    return ResponseEntity.ok(result);
  }

  // 삭제
  @DeleteMapping("/recipe/{id}")
  public Long deleteRecipe(@PathVariable final Long id) throws Exception {
    return modifyRecipeService.deleteRecipe(id);
  }

  // 수정
  // @RestControllerAdvice를 추가하는게 필요해 보인다.
  @PatchMapping("/recipe/{id}")
  public Long updateRecipe(@PathVariable Long id, @RequestBody RecipeRequestDto params) throws Exception {
    return modifyRecipeService.updateRecipe(id, params);
  }
}
