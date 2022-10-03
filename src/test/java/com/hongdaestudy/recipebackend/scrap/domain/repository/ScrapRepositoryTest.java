package com.hongdaestudy.recipebackend.scrap.domain.repository;

import com.hongdaestudy.recipebackend.scrap.domain.Scrap;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("ScrapRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ScrapRepositoryTest {

  @Autowired
  ScrapRepository repository;
  final long givenRecipeId = 1L;

  @Nested
  @DisplayName("save 메소드")
  class Describe_save {

    @BeforeEach
    void prepare() {
      repository.deleteAll();
    }

    @Nested
    @DisplayName("스크랩 객체가 주어지면")
    class Context_with_a_scrap {
      final Scrap givenScrap = Scrap.create(
          1L
          , 1L
      );

      @Test
      @DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
      void it_saves_obj_and_returns_a_saved_obj() {
        Assertions.assertNull(givenScrap.getId(), "저장되지 않은 객체는 아이디가 null 이다");

        final Scrap saved = repository.save(givenScrap);

        Assertions.assertNotNull(saved.getId(), "저장된 객체는 아이디가 추가되어 있다");
        Assertions.assertEquals(saved.getRecipeId(), givenRecipeId);
      }

      @Test
      @DisplayName("주어진 객체의 중복여부를 체크하고 결과를 리턴한다")
      void it_check_duplication_and_returns_result() {
        repository.save(givenScrap);
        final boolean result = repository.existsByRecipeIdAndUserId(givenScrap.getRecipeId(), givenScrap.getUserId());

        Assertions.assertEquals(result, true, "이미 저장된 객체입니다.");
      }
    }
  }
}