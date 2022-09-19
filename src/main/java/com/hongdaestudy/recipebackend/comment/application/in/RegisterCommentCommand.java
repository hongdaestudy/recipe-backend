package com.hongdaestudy.recipebackend.comment.application.in;

import lombok.Data;

@Data
public class RegisterCommentCommand {
    private Long id;

    private Long recipeId;

    private Long userId;

    private String content;

    private Long parentCommentId;

    private Integer level;

    private Integer sort;

    private String score;

    private Long photoFileId;
}