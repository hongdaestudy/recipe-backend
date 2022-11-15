package com.hongdaestudy.recipebackend.comment.application;

import com.hongdaestudy.recipebackend.comment.application.in.RegisterCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.out.RegisterCommentCommandResult;
import com.hongdaestudy.recipebackend.comment.domain.Comment;
import com.hongdaestudy.recipebackend.comment.domain.repository.CommentRepository;
import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterCommentService {
  private final CommentRepository commentRepository;

  @Transactional
  public RegisterCommentCommandResult registerComment(RegisterCommentCommand registerRecipeCommentCommand) throws Exception {

    Comment comment = from(registerRecipeCommentCommand);
    Comment saved = commentRepository.save(comment);

    return new RegisterCommentCommandResult(saved.getId());
  }

  private Comment from(RegisterCommentCommand registerCommentCommand) throws Exception {

    Optional<Comment> parent = Optional.empty();
    // 자식댓글인 경우, 부모 댓글 검증
    if (registerCommentCommand.getParentCommentId() != null) {
      parent = commentRepository.findById(registerCommentCommand.getParentCommentId());
      parent.orElseThrow(() -> new Exception(ErrorCode.COMMENT_NOT_FOUND.getMessage()));
    }
    return Comment.create(
        registerCommentCommand.getRecipeId(),
        registerCommentCommand.getUserId(),
        registerCommentCommand.getContent(),
        parent,
        registerCommentCommand.getLevel(),
        registerCommentCommand.getSort(),
        registerCommentCommand.getScore(),
        registerCommentCommand.getPhotoFileId());
  }

}