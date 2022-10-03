package com.hongdaestudy.recipebackend.scrap.application;


import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.config.error.exception.DuplicationException;
import com.hongdaestudy.recipebackend.scrap.application.in.RegisterScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.out.RegisterScrapCommandResult;
import com.hongdaestudy.recipebackend.scrap.domain.Scrap;
import com.hongdaestudy.recipebackend.scrap.domain.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterScrapService {
  private final ScrapRepository scrapRepository;

  @Transactional
  public RegisterScrapCommandResult registerScrap(RegisterScrapCommand registerRecipeScrapCommand) throws Exception {
    Scrap scrap = from(registerRecipeScrapCommand);
    duplicationCheck(scrap);
    scrapRepository.save(scrap);

    return new RegisterScrapCommandResult(scrap.getRecipeId());
  }

  private Scrap from(RegisterScrapCommand registerScrapCommand) {
    return Scrap.create(
        registerScrapCommand.getRecipeId(),
        registerScrapCommand.getUserId()
    );
  }

  private void duplicationCheck(Scrap scrap) throws Exception {
    boolean duplicationCheck = scrapRepository.existsByRecipeIdAndUserId(scrap.getRecipeId(), scrap.getUserId());
    if (duplicationCheck) {
      throw new DuplicationException("이미 스크랩된 레시피입니다.", ErrorCode.SCRAP_DUPLICATION);
    }
  }

}
