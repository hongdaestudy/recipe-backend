package com.hongdaestudy.recipebackend.comment.domain.repository;

import com.hongdaestudy.recipebackend.comment.application.out.RetrieveCommentCommandResult;
import com.hongdaestudy.recipebackend.comment.domain.Comment;
import com.hongdaestudy.recipebackend.config.TestConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(TestConfig.class)
@DisplayName("CommentRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  CommentRepository repository;
  final String givenContent = "맛있어요!";

  @Nested
  @DisplayName("save 메소드")
  class Describe_save1 {

    @BeforeEach
    void prepare() {
      //repository.deleteAll();
    }

    @Nested
    @DisplayName("댓글 객체가 주어지면")
    class Context_with_a_comment {
      final Comment givenComment = Comment.create(
          1L
          , 1L
          , givenContent
          , java.util.Optional.empty()
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
        Assertions.assertEquals(saved.getContent(), givenContent, "저장된 객체가 동일합니다.");
      }
    }
  }
  @Nested
  @DisplayName("findAllByRecipeId 메소드")
  class Describe_findAllByRecipeId {

    @BeforeEach
    void prepare() {
      //repository.deleteAll();
    }

    @Nested
    @DisplayName("댓글 객체리스트가 주어지면")
    class Context_with_comments {
      Comment givenParent = Comment.create(2L, 1L, givenContent, java.util.Optional.empty(), 0, 0, "1.0", 1L);
      Comment givenChild = Comment.create(2L, 1L, givenContent, java.util.Optional.of(givenParent), 1, 0, "2.0", 2L);
      Comment givenChild2 = Comment.create(2L, 1L, givenContent, java.util.Optional.ofNullable(givenParent), 1, 1, "3.0", 3L);
      Comment givenChild3 = Comment.create(2L, 1L, givenContent, java.util.Optional.ofNullable(givenChild), 2, 1, "4.0", 4L);

      @Test
      @DisplayName("주어진 객체리스트를 저장하고, 저장된 객체를 리턴한다")
      void it_saves_obj_and_returns_a_saved_obj() {
        repository.save(givenParent);
        repository.save(givenChild);
        repository.save(givenChild2);
        repository.save(givenChild3);

        List<RetrieveCommentCommandResult> list = repository.findAllByRecipeId(2L);

        Assertions.assertEquals(list.stream().count(), 4, "객체 조회 성공");

        for (RetrieveCommentCommandResult comment : list) {
          compareComment(list.get(0), givenParent);
          compareComment(list.get(1), givenChild);
          compareComment(list.get(2), givenChild2);
          compareComment(list.get(3), givenChild3);
        }
      }
    }
  }

  static void compareComment(RetrieveCommentCommandResult retrieve, Comment saved) {
    Assertions.assertEquals(retrieve.getRecipeId(), saved.getRecipeId());
    Assertions.assertEquals(retrieve.getUserId(), saved.getUserId());
    Assertions.assertEquals(retrieve.getContent(), saved.getContent());
    Assertions.assertEquals(retrieve.getLevel(), saved.getLevel());
    if (retrieve.getLevel() == 0) {
      Assertions.assertNull(retrieve.getParentCommentId());
      Assertions.assertNull(saved.getParent());
    } else {
      Assertions.assertEquals(retrieve.getParentCommentId(), saved.getParent().getId());
    }
    Assertions.assertEquals(retrieve.getSort(), saved.getSort());
    Assertions.assertEquals(retrieve.getScore(), saved.getScore());
    Assertions.assertEquals(retrieve.getPhotoFileId(), saved.getPhotoFileId());
  }
}
