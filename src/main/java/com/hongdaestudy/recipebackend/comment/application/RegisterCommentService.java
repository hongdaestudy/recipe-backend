package com.hongdaestudy.recipebackend.comment.application;

import com.hongdaestudy.recipebackend.comment.application.in.RegisterCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.out.RegisterCommentCommandResult;
import com.hongdaestudy.recipebackend.comment.domain.Comment;
import com.hongdaestudy.recipebackend.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterCommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public RegisterCommentCommandResult registerComment(RegisterCommentCommand registerRecipeCommentCommand) {
        Comment comment = from(registerRecipeCommentCommand);
        commentRepository.save(comment);

        return new RegisterCommentCommandResult(comment.getId());
    }

    private Comment from(RegisterCommentCommand registerCommentCommand) {
        return Comment.create(
                registerCommentCommand.getRecipeId(),
                registerCommentCommand.getUserId(),
                registerCommentCommand.getContent(),
                registerCommentCommand.getParentCommentId(),
                registerCommentCommand.getLevel(),
                registerCommentCommand.getSort(),
                registerCommentCommand.getScore(),
                registerCommentCommand.getPhotoFileId());
    }

}