package com.hongdaestudy.recipebackend.user.application.out;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
public class UserInfoCommandResult {

  private Long userId;
  private String email;
  private Long userProfileId;
  private Long backgroundFileId;
  private String nickname;
  private Long profileFileId;

  @QueryProjection
  public UserInfoCommandResult(Long userId, String email, Long userProfileId, Long backgroundFileId, String nickname, Long profileFileId) {
    this.userId = userId;
    this.email = email;
    this.userProfileId = userProfileId;
    this.backgroundFileId = backgroundFileId;
    this.nickname = nickname;
    this.profileFileId = profileFileId;
  }
}
