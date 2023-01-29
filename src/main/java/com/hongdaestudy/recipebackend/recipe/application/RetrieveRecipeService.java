package com.hongdaestudy.recipebackend.recipe.application;

//import com.hongdaestudy.recipebackend.ingredient.application.RetrieveIngredientGroupService;

import com.hongdaestudy.recipebackend.ingredient.application.RetrieveIngredientGroupService;
import com.hongdaestudy.recipebackend.ingredient.application.out.RetrieveIngredientGroupCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveRecipeService {
  private final RecipeRepository recipeRepository;
  private final RetrieveIngredientGroupService retrieveIngredientGroupService;

  @Transactional
  public RetrieveRecipeCommandResult retrieveRecipe(RetrieveRecipeCommand retrieveRecipeCommand) {

    RetrieveRecipeCommandResult recipe = recipeRepository.findOneByRecipeId(retrieveRecipeCommand.getRecipeId());
    List<RetrieveIngredientGroupCommandResult> ingredientGroupList = retrieveIngredientGroupService.retrieveIngredientGroups(recipe.getId());
    ingredientGroupList.forEach(recipe::addIngredientGroup);

    return recipe;
  }

  @Transactional
  public List<RetrieveRecipeCommandResult> retrieveRecipeList(RetrieveRecipeCommand retrieveRecipeCommand) {

    List<RetrieveRecipeCommandResult> recipeList = recipeRepository.findAll().stream().map(recipe -> new RetrieveRecipeCommandResult(
          recipe.getId()
        , recipe.getCreatedAt()
        , recipe.getUpdatedAt()
        , recipe.getMemberId()
        //, recipe.recipeSteps
        , recipe.getTitle()
        , recipe.getDescription()
        , recipe.getVideoUrl()
        , recipe.getInformation().getServingCount()
        , recipe.getInformation().getCookingTime()
        , recipe.getInformation().getDifficultyLevel()
        , recipe.getMainPhotoFileId()
        , recipe.getCompletionPhotoFileId()
        , recipe.getTip()
        //, recipe.recipeTags
        , recipe.getStatus()
        , recipe.getDeleteAt())).collect(Collectors.toList());

    return recipeList;
  }
}
