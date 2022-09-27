package com.hongdaestudy.recipebackend.scrap.application;


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
      boolean duplicationCheck = scrapRepository.existsByRecipeIdAndUserID(scrap.getRecipeId(),scrap.getUserId());
      if(duplicationCheck){
        // TODO sieun: 커스텀 Exception 생성
          throw new Exception("이미 스크랩된 레시피 입니다.");
      }
    }

  }
