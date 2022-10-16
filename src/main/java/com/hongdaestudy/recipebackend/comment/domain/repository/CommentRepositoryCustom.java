package com.hongdaestudy.recipebackend.comment.domain.repository;

import com.hongdaestudy.recipebackend.comment.application.out.RetrieveCommentCommandResult;

import java.util.List;

public interface CommentRepositoryCustom {
  List<RetrieveCommentCommandResult> findAllByRecipeId(long recipeId);
}
