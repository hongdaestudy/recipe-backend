package com.hongdaestudy.recipebackend.comment.presentation;

import com.hongdaestudy.recipebackend.comment.application.RegisterCommentService;
import com.hongdaestudy.recipebackend.comment.application.RetrieveCommentService;
import com.hongdaestudy.recipebackend.comment.application.in.RegisterCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.in.RetrieveCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.out.RegisterCommentCommandResult;
import com.hongdaestudy.recipebackend.comment.application.out.RetrieveCommentCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentCommandController {

  private final RegisterCommentService registerCommentService;
  private final RetrieveCommentService retrieveCommentService;

  @PostMapping("/comment")
  public ResponseEntity<RegisterCommentCommandResult> registerComment(@RequestBody RegisterCommentCommand registerCommentCommand) throws Exception {

    RegisterCommentCommandResult result = registerCommentService.registerComment(registerCommentCommand);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/comments")
  public ResponseEntity<List<RetrieveCommentCommandResult>> retrieveComment(@RequestBody RetrieveCommentCommand retrieveCommentCommand) {

    List<RetrieveCommentCommandResult> result = retrieveCommentService.retrieveComment(retrieveCommentCommand);

    return ResponseEntity.ok(result);
  }

}