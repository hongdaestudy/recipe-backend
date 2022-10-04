package com.hongdaestudy.recipebackend.comment.application;

import com.hongdaestudy.recipebackend.comment.application.in.RegisterCommentCommand;
import com.hongdaestudy.recipebackend.comment.application.out.RegisterCommentCommandResult;
import com.hongdaestudy.recipebackend.comment.domain.Comment;
import com.hongdaestudy.recipebackend.comment.domain.repository.CommentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@DisplayName("RegisterCommentServiceTest")
public class RegisterCommentServiceTest {

  @Autowired
  RegisterCommentService registerCommentService;
  @Autowired
  CommentRepository repository;

  final String givenContent = "맛있어요!";

  @Nested
  @DisplayName("save 메소드")
  class Describe_save {

    @BeforeEach
    void prepare() {
      registerCommentService = new RegisterCommentService(repository);
      repository.deleteAll();
    }

    @Nested
    @DisplayName("등록 객체가 주어지면")
    class Context_with_a_comment {
      RegisterCommentCommand command = new RegisterCommentCommand(1L, 1L, givenContent, null, 0, 0, "4.5", null);

      @Test
      @DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
      void it_saves_obj_and_returns_a_saved_obj() {

        RegisterCommentCommandResult result = registerCommentService.registerComment(command);
        Assertions.assertNotNull(result.getId(), "저장된 객체는 아이디가 추가되어 있다");

        Optional<Comment> saved = repository.findById(result.getId());
        Assertions.assertEquals(saved.get().getContent(), givenContent);
      }
    }
  }
}
