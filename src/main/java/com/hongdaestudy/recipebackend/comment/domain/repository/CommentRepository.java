package com.hongdaestudy.recipebackend.comment.domain.repository;


import com.hongdaestudy.recipebackend.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
