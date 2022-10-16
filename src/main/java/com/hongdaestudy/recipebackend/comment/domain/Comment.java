package com.hongdaestudy.recipebackend.comment.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

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
  private Long recipeId;

  @NotNull
  private Long userId;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_comment_id")
  private Comment parent;

  @NotNull
  private int level;

  private int sort;

  private String score;

  private Long photoFileId;


  public static Comment create(
      Long recipeId
      , Long userId
      , String content
      , Optional<Comment> parent
      , int level
      , int sort
      , String score
      , Long photoFileId) {

    Comment comment = new Comment();
    comment.recipeId = recipeId;
    comment.userId = userId;
    comment.content = content;
    if (parent.isPresent()) {
      comment.parent = parent.get();
    }
    comment.level = level;
    comment.sort = sort;
    comment.score = score;
    comment.photoFileId = photoFileId;
    return comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Comment comment = (Comment) o;
    return id != null && Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}