package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public List<UserInfoCommandResult> findAll(UserInfoCommand params) {
        return userRepository.findAll(params);
    }

    @Transactional
    public List<RetrieveRecipeCommandResult> findAllNotDeletedRecipesById(RetrieveRecipeCommand params) {
        return recipeRepository.findAllNotDeletedRecipesById(params);
    }
}
