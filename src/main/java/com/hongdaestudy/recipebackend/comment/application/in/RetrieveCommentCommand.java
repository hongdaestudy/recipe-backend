package com.hongdaestudy.recipebackend.comment.application.in;

import com.hongdaestudy.recipebackend.common.SelfValidating;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RetrieveCommentCommand extends SelfValidating<RetrieveCommentCommand> {

  @NotNull
  private long recipeId;

  private long userId;

  public RetrieveCommentCommand(long recipeId, long userId) {

    this.recipeId = recipeId;
    this.userId = userId;

    this.validateSelf();
  }
}