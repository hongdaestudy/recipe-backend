package com.hongdaestudy.recipebackend.comment.application.in;

import com.hongdaestudy.recipebackend.common.SelfValidating;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterCommentCommand extends SelfValidating<RegisterCommentCommand> {

  @NotNull
  private Long recipeId;

  private Long userId;

  private String content;

  private Long parentCommentId;

  @NotNull
  private Integer level;

  private Integer sort;

  private String score;

  private Long photoFileId;

  public RegisterCommentCommand(Long recipeId, Long userId, String content, Long parentCommentId, Integer level, Integer sort, String score, Long photoFileId) {

    this.recipeId = recipeId;
    this.userId = userId;
    this.content = content;
    this.parentCommentId = parentCommentId;
    this.level = level;
    this.sort = sort;
    this.score = score;
    this.photoFileId = photoFileId;

    this.validateSelf();
  }
}