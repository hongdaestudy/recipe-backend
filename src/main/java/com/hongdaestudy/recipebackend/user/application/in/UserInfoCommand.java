package com.hongdaestudy.recipebackend.user.application.in;

import lombok.Getter;

@Getter
public class UserInfoCommand {

    private Long userId;
    private String email;
    private Long userProfileId;
    private String nickname;
    private Long backgroundFileId;
    private Long profileId;
    private String srchCondition;
    private int limitPage = 100;
}
