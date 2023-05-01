package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hongdaestudy.recipebackend.user.domain.QUser.user;
import static com.hongdaestudy.recipebackend.user.domain.QUserProfile.userProfile;
import static com.hongdaestudy.recipebackend.user.domain.QUserFollow.userFollow;

import static com.querydsl.core.types.ExpressionUtils.*;
import static com.querydsl.core.types.Order.ASC;
import static com.querydsl.core.types.Order.DESC;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public UserRepositoryImpl(JPAQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<UserInfoCommandResult> findAll(UserInfoCommand userInfoCommand, Pageable pageable) {

    QueryResults<UserInfoCommandResult> results = queryFactory
        .select(Projections.constructor(UserInfoCommandResult.class,
            user.id,
            user.email,
            userProfile.id,
            userProfile.backgroundFileId,
            userProfile.nickname,
            userProfile.profileFileId,
            as(select(userFollow.count()).from(userFollow).where(userFollow.following.id.eq(user.id)), "followerCnt")
        ))
        .from(user).innerJoin(userProfile).on(user.id.eq(userProfile.id))
        .where(likeNickname(userInfoCommand.getNickname()))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(sortByField(userInfoCommand.getSrchCondition()))
        .fetchResults();

    List<UserInfoCommandResult> content = results.getResults();

    long total = results.getTotal();

    return new PageImpl<>(content, pageable, total);
  }

  // todo: 소식받기 순위, 최근활동쉐프, 새로운쉐프 정렬 필요
  private OrderSpecifier sortByField(String params) {

    if (params != null && params.equals("new")) {
      return new OrderSpecifier(DESC, user.createdAt);
    }

    if (params != null && params.equals("active")) {
      return new OrderSpecifier(DESC, user.updatedAt);
    }

    return new OrderSpecifier(ASC, user.id);
  }

  private BooleanExpression likeNickname(String nickname) {
    if (nickname != null && !nickname.isEmpty()) {
      return userProfile.nickname.contains(nickname);
    }
    return null;
  }
}
