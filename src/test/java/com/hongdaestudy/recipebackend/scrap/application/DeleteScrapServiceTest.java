package com.hongdaestudy.recipebackend.scrap.application;

import com.hongdaestudy.recipebackend.scrap.application.in.DeleteScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.out.DeleteScrapCommandResult;
import com.hongdaestudy.recipebackend.scrap.domain.Scrap;
import com.hongdaestudy.recipebackend.scrap.domain.repository.ScrapRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeleteScrapServiceTest")
public class DeleteScrapServiceTest {

  @Mock
  private ScrapRepository scrapRepository;

  @InjectMocks
  private DeleteScrapService deleteScrapService;

  final Scrap givenScrap = Scrap.create(1L, 1L);

  @Nested
  @DisplayName("delete 메소드")
  class Describe_delete {

    @BeforeEach
    void prepare() {

    }

    @Nested
    @DisplayName("null 변수가 주어지면")
    class Context_with_null_recipe_id {
      @Test
      @DisplayName("주어진 데이터로 객체를 생성하고, 에러를 발생한다.")
      void it_gives_null_obj_and_returns_exception() {
        try {
          assertThrows(ConstraintViolationException.class, () -> {
            new DeleteScrapCommand(null, 1L);
          });
        } catch (Exception e) {

        }

      }

    }

    @Nested
    @DisplayName("삭제 객체가 주어지면")
    class Context_with_a_scrap {
      DeleteScrapCommand command = new DeleteScrapCommand(1L, 1L);

      @Test
      @DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
      void it_deletes_obj_and_returns_a_deleted_obj() {
        try {
          DeleteScrapCommandResult result = deleteScrapService.deleteScrap(command);
          Assertions.assertEquals(result.getRecipeId(), command.getRecipeId(), "객체가 삭제되었습니다.");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    }
  }
}