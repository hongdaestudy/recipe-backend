package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.ingredient.application.RegisterIngredientGroupService;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RegisterRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterRecipeService {
  private final RecipeRepository recipeRepository;
  private final RegisterIngredientGroupService registerIngredientGroupService;

  @Transactional
  public RegisterRecipeCommandResult registerRecipe(RegisterRecipeCommand registerRecipeCommand) {
    Recipe recipe = from(registerRecipeCommand);
    recipeRepository.save(recipe);

    registerIngredientGroupService.registerIngredientGroups(
        recipe.getId(), registerRecipeCommand.getIngredientGroups()
    );
    List<String> ingredients = registerRecipeCommand.getIngredientGroups().stream().map(group -> group.getIngredients())
            .flatMap(List::stream).map(x -> x.getName()).distinct()
            .collect(Collectors.toList());
    return new RegisterRecipeCommandResult(recipe.getId());
  }

  private Recipe from(RegisterRecipeCommand registerRecipeCommand) {
    List<RecipeStep> recipeSteps = registerRecipeCommand.getRecipeSteps().stream()
        .map(recipeStepCommand -> RecipeStep.create(
            recipeStepCommand.getDescription(),
            recipeStepCommand.getPhotoFileId(),
            recipeStepCommand.getSort()))
        .collect(Collectors.toList());

    List<RecipeTag> recipeTags = registerRecipeCommand.getRecipeTags().stream()
        .map(recipeTagCommand -> RecipeTag.create(
            recipeTagCommand.getName(),
            recipeTagCommand.getSort()))
        .collect(Collectors.toList());

    RecipeCategory recipeCategory = RecipeCategory.create(
            registerRecipeCommand.getCategory().getKind(),
            registerRecipeCommand.getCategory().getSituation(),
            registerRecipeCommand.getCategory().getMethod(),
            registerRecipeCommand.getCategory().getIngredient());

    RecipeInformation recipeInformation = RecipeInformation.create(
        registerRecipeCommand.getInformation().getServingCount(),
        registerRecipeCommand.getInformation().getCookingTime(),
        registerRecipeCommand.getInformation().getDifficultyLevel());

    return Recipe.create(
        registerRecipeCommand.getCompletionPhotoFileId(), //TODO 다중파일 데이터.. 파일 테이블 구조 변경?
        registerRecipeCommand.getDescription(),
        recipeCategory,
        recipeInformation,
        registerRecipeCommand.getMainPhotoFileId(),
        registerRecipeCommand.getMemberId(),
        registerRecipeCommand.getStatus(),
        registerRecipeCommand.getTip(),
        registerRecipeCommand.getTitle(),
        registerRecipeCommand.getVideoUrl(),
        recipeSteps,
        recipeTags,
        'N');
  }
}
