package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.ingredient.application.RegisterIngredientGroupService;
import com.hongdaestudy.recipebackend.ingredient.application.in.RegisterIngredientGroupCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeInformation;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeStep;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeTag;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// TODO jaesay: interface 로 정의할지
@Service
@RequiredArgsConstructor
public class RegisterRecipeService {
  private final RecipeRepository recipeRepository;
  private final RegisterIngredientGroupService registerIngredientGroupService;

  @Transactional
  public RecipeCommandResult registerRecipe(RegisterRegisterRecipeCommand registerRegisterRecipeCommand, List<RegisterIngredientGroupCommand> registerIngredientGroupCommands) {
    Recipe recipe = from(registerRegisterRecipeCommand);
    recipeRepository.save(recipe);
    registerIngredientGroupService.registerIngredientGroups(recipe.getId(), registerIngredientGroupCommands);

    return new RecipeCommandResult(recipe.getId().getValue());
  }

  private Recipe from(RegisterRegisterRecipeCommand registerRegisterRecipeCommand) {
    List<RecipeStep> recipeSteps =
        registerRegisterRecipeCommand.getRecipeSteps().stream()
            .map(recipeStepCommand -> RecipeStep.create(
                    recipeStepCommand.getDescription(),
                    recipeStepCommand.getPhotoUrl(),
                    recipeStepCommand.getSort()))
            .collect(Collectors.toList());

    List<RecipeTag> recipeTags =
        registerRegisterRecipeCommand.getRecipeTags().stream()
            .map(recipeTagCommand -> RecipeTag.create(
                        recipeTagCommand.getName(),
                        recipeTagCommand.getSort()))
            .collect(Collectors.toList());

    RecipeInformation recipeInformation = RecipeInformation.create(
        registerRegisterRecipeCommand.getInformation().getServingCount(),
        registerRegisterRecipeCommand.getInformation().getCookingTime(),
        registerRegisterRecipeCommand.getInformation().getDifficultyLevel());

    return Recipe.create(
        registerRegisterRecipeCommand.getMemberId(),
        registerRegisterRecipeCommand.getTitle(),
        registerRegisterRecipeCommand.getDescription(),
        registerRegisterRecipeCommand.getVideoFileId(),
        recipeInformation,
        registerRegisterRecipeCommand.getCompletionPhotoFileId(),
        registerRegisterRecipeCommand.getTip(),
        recipeSteps,
        recipeTags,
        registerRegisterRecipeCommand.getStatus());
  }
}
