package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.recipe.application.in.RecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.mapper.RecipeMapper;
import com.hongdaestudy.recipebackend.recipe.application.out.RecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeEntity;
import com.hongdaestudy.recipebackend.recipe.domain.service.RecipeIngredientService;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO jaesay: interface 로 정의할지
@Service
@RequiredArgsConstructor
public class RegisterRecipeService {
  private final RecipeMapper recipeMapper;
  private final RecipeRepository recipeRepository;
  private final RecipeIngredientService recipeIngredientClient;

  @Transactional
  public RecipeCommandResult createRecipe(RecipeCommand command) {
    // TODO jaesay: validation 위치 확인 : command는 컨트롤러에서 나머지는 서비스에서 or 서비스에서만 or 컨트롤러 또는 command
    // 객체에서.. 등등

    // TODO jaesay: 다른 바운더리 컨텍스트 데이터 어떻게 통신할지? (e.g. ingredient), 재료는 레시피랑 같은 바운더리 컨텍스트로 합칠지..
    recipeIngredientClient.createIngredients();
    RecipeEntity recipeEntity = recipeMapper.toEntity(command);
    recipeRepository.save(recipeEntity);

    return recipeMapper.toCommandResult(recipeEntity);
  }
}
