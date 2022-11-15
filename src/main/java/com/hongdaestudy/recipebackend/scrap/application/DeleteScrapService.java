package com.hongdaestudy.recipebackend.scrap.application;


import com.hongdaestudy.recipebackend.scrap.application.in.DeleteScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.out.DeleteScrapCommandResult;
import com.hongdaestudy.recipebackend.scrap.domain.Scrap;
import com.hongdaestudy.recipebackend.scrap.domain.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteScrapService {
  private final ScrapRepository scrapRepository;

  @Transactional
  public DeleteScrapCommandResult deleteScrap(DeleteScrapCommand deleteRecipeScrapCommand) throws Exception {
    Scrap scrap = from(deleteRecipeScrapCommand);
    scrapRepository.deleteByRecipeIdAndUserId(scrap.getRecipeId(), scrap.getUserId());

    return new DeleteScrapCommandResult(scrap.getRecipeId());
  }

  private Scrap from(DeleteScrapCommand deleteScrapCommand) {
    return Scrap.create(
        deleteScrapCommand.getRecipeId(),
        deleteScrapCommand.getUserId()
    );
  }

}
