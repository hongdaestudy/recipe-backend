package com.hongdaestudy.recipebackend.comment.domain.repository;

import com.hongdaestudy.recipebackend.comment.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    void 댓글_등록() {
        // given
        Comment command = Comment.create(
                1L
                ,1L
                ,"좋아요"
                ,1L
                ,0
                ,0
                ,"4.5"
                ,1L
        );
        // when
        Comment savedComment = commentRepository.save(command);
        assertThat(savedComment.getContent()).isEqualTo("좋아요");
    }
}
