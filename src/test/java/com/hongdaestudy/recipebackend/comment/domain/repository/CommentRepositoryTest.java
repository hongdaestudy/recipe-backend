package com.hongdaestudy.recipebackend.comment.domain.repository;

import com.hongdaestudy.recipebackend.comment.domain.Comment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("CommentRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

  @Autowired
  CommentRepository repository;
  final String givenContent = "맛있어요!";

  @Nested
  @DisplayName("save 메소드")
  class Describe_save {

    @BeforeEach
    void prepare() {
      repository.deleteAll();
    }

    @Nested
    @DisplayName("댓글 객체가 주어지면")
    class Context_with_a_comment {
      final Comment givenComment = Comment.create(
          1L
          , 1L
          , givenContent
          , null
          , 0
          , 0
          , "4.5"
          , 1L
      );

      @Test
      @DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
      void it_saves_obj_and_returns_a_saved_obj() {
        Assertions.assertNull(givenComment.getId(), "저장되지 않은 객체는 아이디가 null 이다");

        final Comment saved = repository.save(givenComment);

        Assertions.assertNotNull(saved.getId(), "저장된 객체는 아이디가 추가되어 있다");
        Assertions.assertEquals(saved.getContent(), givenContent);
      }
    }
  }
}
