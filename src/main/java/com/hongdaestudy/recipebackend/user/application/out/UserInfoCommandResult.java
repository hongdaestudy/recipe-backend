package com.hongdaestudy.recipebackend.user.application.out;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
public class UserInfoCommandResult {

  private Long id;
  private String email;
  private Long userProfileId;
  private Long backgroundFileId;
  private String nickname;
  private Long profileFileId;
  private Long followerId;
  private Long followingId;
  private Long followerCnt;

  @QueryProjection
  public UserInfoCommandResult(Long id, String email, Long userProfileId, Long backgroundFileId, String nickname, Long profileFileId, Long followerCnt) {
    this.id = id;
    this.email = email;
    this.backgroundFileId = backgroundFileId;
    this.nickname = nickname;
    this.profileFileId = profileFileId;
    this.followerCnt = followerCnt;
  }

  public UserInfoCommandResult(Long id) {
    this.id = id;
  }
}
