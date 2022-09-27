package com.hongdaestudy.recipebackend.scrap;

import com.hongdaestudy.recipebackend.scrap.application.RegisterScrapService;
import com.hongdaestudy.recipebackend.scrap.application.in.RegisterScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.out.RegisterScrapCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScrapCommandController {

  private final RegisterScrapService registerScrapService;

  @ExceptionHandler
  public ResponseEntity controllerExceptionHandler(Exception e) {
    // TODO sieun: 에러/성공 메세지 처리 방식
    return ResponseEntity.ok().body(e.getMessage());
  }

  @PostMapping("/scrap")
  public ResponseEntity<RegisterScrapCommandResult> registerScrap(@RequestBody RegisterScrapCommand registerScrapCommand) throws Exception {

    RegisterScrapCommandResult result = registerScrapService.registerScrap(registerScrapCommand);

    return ResponseEntity.ok(result);
  }

}