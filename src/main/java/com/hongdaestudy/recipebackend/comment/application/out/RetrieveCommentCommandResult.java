package com.hongdaestudy.recipebackend.comment.application.out;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveCommentCommandResult {
  @NotNull
  private Long id;

  @NotNull
  private Long recipeId;

  private Long userId;

  private String userNickname;

  private Long profileFileId;

  private String content;

  private Long parentCommentId;

  @NotNull
  private Integer level;

  private Integer sort;

  private String score;

  private Long photoFileId;

  private List<RetrieveCommentCommandResult> children;

  @QueryProjection
  public RetrieveCommentCommandResult(Long id, Long recipeId, Long userId, String userNickname, Long profileFileId, String content, Long parentCommentId, Integer level, Integer sort, String score, Long photoFileId) {
    this.id = id;
    this.recipeId = recipeId;
    this.userId = userId;
    this.userNickname = userNickname;
    this.profileFileId = profileFileId;
    this.content = content;
    this.parentCommentId = parentCommentId;
    this.level = level;
    this.sort = sort;
    this.score = score;
    this.photoFileId = photoFileId;
    this.children = new ArrayList<>();
  }


}
