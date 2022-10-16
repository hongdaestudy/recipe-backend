package com.hongdaestudy.recipebackend.comment.application;

import com.hongdaestudy.recipebackend.comment.application.in.RetrieveCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.out.RetrieveCommentCommandResult;
import com.hongdaestudy.recipebackend.comment.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RetrieveCommentService {
  private final CommentRepository commentRepository;

  @Transactional
  public List<RetrieveCommentCommandResult> retrieveComment(RetrieveCommentCommand retrieveCommentCommand) {

    List<RetrieveCommentCommandResult> commentList = commentRepository.findAllByRecipeId(retrieveCommentCommand.getRecipeId());

    return convertNestedStructure(commentList);
  }

  private List<RetrieveCommentCommandResult> convertNestedStructure(List<RetrieveCommentCommandResult> comments) {
    List<RetrieveCommentCommandResult> result = new ArrayList<>();
    Map<Long, RetrieveCommentCommandResult> map = new HashMap<>();
    comments.stream().forEach(comment -> {
      map.put(comment.getId(), comment);
      if (comment.getParentCommentId() != null) map.get(comment.getParentCommentId()).getChildren().add(comment);
      else result.add(comment);
    });
    return result;
  }

}