package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hongdaestudy.recipebackend.user.domain.QUser.user;
import static com.hongdaestudy.recipebackend.user.domain.QUserProfile.userProfile;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public UserRepositoryImpl(JPAQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  @Override
  public List<UserInfoCommandResult> findAll(UserInfoCommand userInfoCommand) {

    List<UserInfoCommandResult> userInfoEntity = queryFactory.
        select(Projections.constructor(UserInfoCommandResult.class, user.id, user.email, userProfile.id, userProfile.backgroundFileId, userProfile.nickname, userProfile.profileFileId))
        .from(user)
        .innerJoin(userProfile)
        .on(user.id.eq(userProfile.id))
        .fetch();
    return userInfoEntity;
  }
}
