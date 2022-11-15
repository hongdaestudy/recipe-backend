package com.hongdaestudy.recipebackend.scrap.application;

import com.hongdaestudy.recipebackend.config.error.exception.DuplicationException;
import com.hongdaestudy.recipebackend.scrap.application.in.RegisterScrapCommand;
import com.hongdaestudy.recipebackend.scrap.application.out.RegisterScrapCommandResult;
import com.hongdaestudy.recipebackend.scrap.domain.Scrap;
import com.hongdaestudy.recipebackend.scrap.domain.repository.ScrapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("RegisterScrapServiceTest")
public class RegisterScrapServiceTest {

  @Mock
  private ScrapRepository scrapRepository;

  @InjectMocks
  private RegisterScrapService registerScrapService;

  final Scrap givenScrap = Scrap.create(1L, 1L);

  @Nested
  @DisplayName("save 메소드")
  class Describe_save {

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
            new RegisterScrapCommand(null, 1L);
          });
        } catch (Exception e) {

        }

      }

    }
    @Nested
    @DisplayName("등록 객체가 주어지면")
    class Context_with_a_scrap {
      RegisterScrapCommand command = new RegisterScrapCommand(1L, 1L);

      @Test
      @DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
      void it_saves_obj_and_returns_a_saved_obj() {
        try {
          given(scrapRepository.existsByRecipeIdAndUserId(1L, 1L)).willReturn(false);
          given(scrapRepository.save(givenScrap)).willReturn(givenScrap);

          RegisterScrapCommandResult result = registerScrapService.registerScrap(command);

          assertEquals(result.getRecipeId(), command.getRecipeId());
          assertEquals(result.getRecipeId(), command.getRecipeId());


        } catch (Exception e) {

        }

      }
      @Test
      @DisplayName("중복 객체를 저장하고, 예외를 리턴한다")
      void it_saves_dup_obj_and_returns_exception() {

        given(scrapRepository.existsByRecipeIdAndUserId(1L, 1L)).willReturn(true);

        assertThrows(DuplicationException.class, () -> {
          registerScrapService.registerScrap(command);
        });
      }

    }
  }
}