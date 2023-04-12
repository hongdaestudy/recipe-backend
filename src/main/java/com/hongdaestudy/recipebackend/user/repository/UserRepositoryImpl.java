package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
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

    List<UserInfoCommandResult> userInfoEntity = queryFactory
        .select(Projections.constructor(UserInfoCommandResult.class,
            user.id,
            user.email,
            userProfile.id,
            userProfile.backgroundFileId,
            userProfile.nickname,
            userProfile.profileFileId))
        .from(user).innerJoin(userProfile).on(user.id.eq(userProfile.id))
        .where(likeNickname(userInfoCommand.getNickname()))
        .orderBy(sortByField(userInfoCommand.getSrchCondition()))
        .fetch();
    return userInfoEntity;
  }

  // todo: 소식받기 순위, 최근활동쉐프, 새로운쉐프 정렬 필요
  private OrderSpecifier sortByField(String params) {
    if (params == null || params.isEmpty()) {
      return new OrderSpecifier(Order.ASC, user.id);
    }
    return null;
  }

  private BooleanExpression likeNickname(String nickname) {
    if (nickname != null && !nickname.isEmpty()) {
      return userProfile.nickname.contains(nickname);
    }
    return null;
  }
}
