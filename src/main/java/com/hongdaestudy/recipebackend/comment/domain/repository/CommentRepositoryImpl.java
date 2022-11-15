package com.hongdaestudy.recipebackend.comment.domain.repository;

import com.hongdaestudy.recipebackend.comment.application.out.RetrieveCommentCommandResult;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hongdaestudy.recipebackend.comment.domain.QComment.comment;
import static com.hongdaestudy.recipebackend.user.domain.QUser.user;


@Repository
public class CommentRepositoryImpl implements CommentRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public CommentRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.queryFactory = jpaQueryFactory;
  }

  @Override
  public List<RetrieveCommentCommandResult> findAllByRecipeId(long recipeId) {

    List<RetrieveCommentCommandResult> comments = queryFactory.select(Projections.constructor(RetrieveCommentCommandResult.class
            , comment.id.as("id")
            , comment.recipeId.as("recipeId")
            , comment.userId.as("userId")
            , ExpressionUtils.as(
                JPAExpressions.select(user.userProfile.nickname)
                    .from(user)
                    .where(user.id.eq(comment.userId)),
                "userNickname")
            , ExpressionUtils.as(
                JPAExpressions.select(user.userProfile.profileFileId)
                    .from(user)
                    .where(user.id.eq(comment.userId)),
                "profileFileId")
            , comment.content.as("content")
            , comment.parent.id.as("parentCommentId")
            , comment.level.as("level")
            , comment.sort.as("sort")
            , comment.score.as("score")
            , comment.photoFileId.as("photoFileId")))
        .from(comment)
        .leftJoin(comment.parent)
        .where(comment.recipeId.eq(recipeId))
        .orderBy(
            comment.parent.id.asc().nullsFirst(),
            comment.sort.asc()
        ).fetch();

    return comments;
  }
}