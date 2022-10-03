package com.hongdaestudy.recipebackend.comment.presentation;

import com.hongdaestudy.recipebackend.comment.application.RegisterCommentService;
import com.hongdaestudy.recipebackend.comment.application.in.RegisterCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.out.RegisterCommentCommandResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentCommandController {

    private final RegisterCommentService registerCommentService;

    @PostMapping("/comment")
    public ResponseEntity<RegisterCommentCommandResult> registerComment(@RequestBody RegisterCommentCommand registerCommentCommand) {

        RegisterCommentCommandResult result = registerCommentService.registerComment(registerCommentCommand);

        return ResponseEntity.ok(result);
    }

}