package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;

import java.util.List;

public interface UserRepositoryCustom {

  List<UserInfoCommandResult> findAll(UserInfoCommand userInfoCommand);
}
