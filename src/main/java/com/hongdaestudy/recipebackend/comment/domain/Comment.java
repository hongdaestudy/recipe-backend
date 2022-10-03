package com.hongdaestudy.recipebackend.comment.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "comment")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Comment extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private Long id;

  @NotNull
  private long recipeId;

  // TODO sieun: userProfileId -> userId (프로필 변경시, 변경전 프로필이 보일수 있음)
  @NotNull
  private long userId;

  private String content;

  private Long parentCommentId;

  @NotNull
  private int level;

  private int sort;

  private String score;

  private Long photoFileId;

  public static Comment create(
      Long recipeId
      , Long userId
      , String content
      , Long parentCommentId
      , int level
      , int sort
      , String score
      , Long photoFileId) {

    Comment comment = new Comment();
    comment.recipeId = recipeId;
    comment.userId = userId;
    comment.content = content;
    comment.parentCommentId = parentCommentId;
    comment.level = level;
    comment.sort = sort;
    comment.score = score;
    comment.photoFileId = photoFileId;

    return comment;
  }
}