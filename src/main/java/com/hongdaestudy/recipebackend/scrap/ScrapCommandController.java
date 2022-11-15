package com.hongdaestudy.recipebackend.scrap;

import com.hongdaestudy.recipebackend.config.error.ErrorResponse;
import com.hongdaestudy.recipebackend.config.error.exception.DuplicationException;
import com.hongdaestudy.recipebackend.scrap.application.DeleteScrapService;
import com.hongdaestudy.recipebackend.scrap.application.RegisterScrapService;
import com.hongdaestudy.recipebackend.scrap.application.in.DeleteScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.in.RegisterScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.out.DeleteScrapCommandResult;
import com.hongdaestudy.recipebackend.scrap.application.out.RegisterScrapCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScrapCommandController {

  private final RegisterScrapService registerScrapService;
  private final DeleteScrapService deleteScrapService;

  @ExceptionHandler
  public ResponseEntity<ErrorResponse> controllerExceptionHandler(DuplicationException e) {
    final ErrorResponse response = new ErrorResponse(e.getMessage(), null);
    return new ResponseEntity<>(response, HttpStatus.valueOf(409));
  }

  @PostMapping("/scrap")
  public ResponseEntity<RegisterScrapCommandResult> registerScrap(@RequestBody RegisterScrapCommand registerScrapCommand) throws Exception {

    RegisterScrapCommandResult result = registerScrapService.registerScrap(registerScrapCommand);

    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/scrap")
  public ResponseEntity<DeleteScrapCommandResult> deleteScrap(@RequestBody DeleteScrapCommand deleteScrapCommand) throws Exception {

    DeleteScrapCommandResult result = deleteScrapService.deleteScrap(deleteScrapCommand);

    return ResponseEntity.ok(result);
  }
}