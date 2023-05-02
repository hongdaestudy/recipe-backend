package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.config.error.exception.CustomException;
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

import static com.hongdaestudy.recipebackend.config.error.ErrorCode.MEMBER_NOT_FOUND;

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
        validateFollow(entity);
        return new UserInfoCommandResult(entity.getFollowing().getId());
    }

    private void validateFollow(UserFollow entity) {
        int result = userFollowRepository.countUserFollow(entity.getFollower().getId(), entity.getFollowing().getId());
        if (result == 0) {
            userFollowRepository.save(entity);
        } else {
            throw new IllegalArgumentException("해당 쉐프는 이미 소식받기를 했습니다.");
        }
    }

    @Transactional
    public UserInfoCommandResult cancelFollow(UserInfoCommand params) {
        UserFollow entity = userFollowRepository.findByFollow(params.getFollowerId(), params.getFollowingId())
                                                .orElseThrow(() -> new IllegalArgumentException("해당 쉐프를 찾을 수 없습니다."));
        entity.cancelFollow(entity);
        return new UserInfoCommandResult(entity.getFollowing().getId());
    }
}
