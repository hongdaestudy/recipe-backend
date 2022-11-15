package com.hongdaestudy.recipebackend.ingredient.application;

import com.hongdaestudy.recipebackend.ingredient.application.out.RetrieveIngredientGroupCommandResult;
import com.hongdaestudy.recipebackend.ingredient.domain.IngredientGroup;
import com.hongdaestudy.recipebackend.ingredient.domain.repository.IngredientGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveIngredientGroupService {

  private final IngredientGroupRepository ingredientGroupRepository;

  @Transactional(propagation = Propagation.MANDATORY)
  public List<RetrieveIngredientGroupCommandResult> retrieveIngredientGroups(final long recipeId) {
    List<IngredientGroup> ingredientGroups = ingredientGroupRepository.findByRecipeIdOrderBySortAsc(recipeId);

    return from(ingredientGroups);
  }

  private List<RetrieveIngredientGroupCommandResult> from(List<IngredientGroup> ingredientGroups) {
    List<RetrieveIngredientGroupCommandResult> retrieveIngredientGroupCommandResult = ingredientGroups.stream()
        .map(ingredientGroupCommand -> {

          List<RetrieveIngredientGroupCommandResult.RetrieveIngredientCommandResult> retrieveIngredientCommandResult = ingredientGroupCommand.getIngredients().stream()
              .map(ingredientCommand -> {
                return RetrieveIngredientGroupCommandResult.RetrieveIngredientCommandResult.create(
                    ingredientCommand.getName(), ingredientCommand.getAmount(), ingredientCommand.getSort());
              }).collect(Collectors.toList());

          return RetrieveIngredientGroupCommandResult.create(
              ingredientGroupCommand.getName(), ingredientGroupCommand.getSort(), retrieveIngredientCommandResult);
        })
        .collect(Collectors.toList());

    return retrieveIngredientGroupCommandResult;
  }
}
