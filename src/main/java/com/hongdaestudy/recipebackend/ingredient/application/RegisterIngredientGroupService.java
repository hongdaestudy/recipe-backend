package com.hongdaestudy.recipebackend.ingredient.application;

import com.hongdaestudy.recipebackend.ingredient.application.in.RegisterIngredientGroupCommand;
import com.hongdaestudy.recipebackend.ingredient.domain.Ingredient;
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
public class RegisterIngredientGroupService {

  private final IngredientGroupRepository ingredientGroupRepository;

  @Transactional(propagation = Propagation.MANDATORY)
  public void registerIngredientGroups(final long recipeId, List<RegisterIngredientGroupCommand> registerIngredientGroupCommands) {
    List<IngredientGroup> ingredientGroups = registerIngredientGroupCommands.stream()
        .map(registerIngredientGroupCommand -> with(recipeId, registerIngredientGroupCommand))
        .collect(Collectors.toList());

    ingredientGroupRepository.saveAll(ingredientGroups);
  }

  private IngredientGroup with(long recipeId, RegisterIngredientGroupCommand registerIngredientGroupCommand) {
    List<Ingredient> ingredients = registerIngredientGroupCommand.getIngredients().stream()
        .map(ingredientCommand -> Ingredient.create(
            ingredientCommand.getName(), ingredientCommand.getAmount(), ingredientCommand.getSort()))
        .collect(Collectors.toList());

    return IngredientGroup.create(recipeId, registerIngredientGroupCommand.getName(), registerIngredientGroupCommand.getSort(), ingredients);
  }
}
