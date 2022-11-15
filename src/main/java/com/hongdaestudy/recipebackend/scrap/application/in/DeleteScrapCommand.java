package com.hongdaestudy.recipebackend.scrap.application.in;

import com.hongdaestudy.recipebackend.common.SelfValidating;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteScrapCommand extends SelfValidating<DeleteScrapCommand> {

  @NotNull
  private Long recipeId;

  private Long userId;

  public DeleteScrapCommand(Long recipeId, Long userId) {

    this.recipeId = recipeId;
    this.userId = userId;

    this.validateSelf();
  }

}
