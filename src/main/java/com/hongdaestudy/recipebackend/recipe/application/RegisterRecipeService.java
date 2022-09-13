package com.hongdaestudy.recipebackend.recipe.application;

import com.hongdaestudy.recipebackend.recipe.application.in.RecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.mapper.RecipeMapper;
import com.hongdaestudy.recipebackend.recipe.application.out.RecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeEntity;
import com.hongdaestudy.recipebackend.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO jaesay: interface 로 정의할지
@Service
@RequiredArgsConstructor
public class RegisterRecipeService {
    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    // TODO jaesay: 메서드명 고민해보기
    @Transactional
    public RecipeCommandResult registerRecipe(RecipeCommand command) {
        // TODO jaesay: validation 위치 확인 : command는 컨트롤러에서 나머지는 서비스에서 or 서비스에서만 or 컨트롤러 또는 command 객체에서.. 등등

        // TODO jaesay: 다른 바운더리 컨텍스트 데이터 어떻게 통신할지? (e.g. ingredient)
        RecipeEntity recipeEntity = recipeMapper.toEntity(command);
        recipeRepository.save(recipeEntity);

        return recipeMapper.toCommandResult(recipeEntity);
    }
}
