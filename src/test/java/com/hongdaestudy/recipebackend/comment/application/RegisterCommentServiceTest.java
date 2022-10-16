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
      void it_saves_obj_and_returns_a_saved_obj() throws Exception {

        RegisterCommentCommandResult result = registerCommentService.registerComment(command);
        Assertions.assertNotNull(result.getId(), "저장된 객체는 아이디가 추가되어 있다");

        Optional<Comment> saved = repository.findById(result.getId());
        Assertions.assertEquals(saved.get().getContent(), givenContent);
      }
    }

    @Nested
    @DisplayName("등록 객체가 주어지면")
    class Context_with_comments {
      RegisterCommentCommand parent = new RegisterCommentCommand(1L, 1L, givenContent, null, 0, 1, "4.5", null);
      RegisterCommentCommand child1 = new RegisterCommentCommand(1L, 2L, givenContent, 1L, 1, 1, "4.0", null);
      RegisterCommentCommand child2 = new RegisterCommentCommand(1L, 3L, givenContent, 1L, 1, 2, "3.0", null);
      RegisterCommentCommand child3 = new RegisterCommentCommand(1L, 4L, givenContent, 2L, 2, 1, "2.0", null);

      @Test
      @DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
      void it_saves_obj_and_returns_a_saved_obj() throws Exception {

        RegisterCommentCommandResult result1 = registerCommentService.registerComment(parent);
        Assertions.assertNotNull(result1.getId(), "저장된 객체는 아이디가 추가되어 있다");

        RegisterCommentCommandResult result2 = registerCommentService.registerComment(child1);
        Assertions.assertNotNull(result2.getId(), "저장된 객체는 아이디가 추가되어 있다");
        RegisterCommentCommandResult result3 = registerCommentService.registerComment(child2);
        Assertions.assertNotNull(result3.getId(), "저장된 객체는 아이디가 추가되어 있다");
        RegisterCommentCommandResult result4 = registerCommentService.registerComment(child3);
        Assertions.assertNotNull(result4.getId(), "저장된 객체는 아이디가 추가되어 있다");

      }
    }
  }
}
