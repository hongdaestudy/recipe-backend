package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.hongdaestudy.recipebackend.user.domain.UserFollow;
import com.hongdaestudy.recipebackend.user.repository.UserFollowRepository;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public Page<UserInfoCommandResult> findAll(UserInfoCommand params, Pageable pageable) {
        return userRepository.findAll(params, pageable);
    }

    @Transactional
    public Page<RetrieveRecipeCommandResult> findAllNotDeletedRecipesById(RetrieveRecipeCommand params, Pageable pageable) {
        return recipeRepository.findAllNotDeletedRecipesById(params, pageable);
    }

    @Transactional
    public UserInfoCommandResult registerFollow(UserInfoCommand params) {
        UserFollow entity = UserFollow.saveFollow(params);
        userFollowRepository.save(entity);
        return new UserInfoCommandResult(entity.getFollowing().getId());
    }
}
