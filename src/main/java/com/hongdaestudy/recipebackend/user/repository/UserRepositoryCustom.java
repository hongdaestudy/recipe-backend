package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
  Page<UserInfoCommandResult> findAll(UserInfoCommand userInfoCommand, Pageable pageable);
}
