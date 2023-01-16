package com.hongdaestudy.recipebackend.recipe.repository;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import com.hongdaestudy.recipebackend.recipe.presentation.RecipeCommandController;
import com.querydsl.core.types.Projections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.hongdaestudy.recipebackend.recipe.domain.QRecipe.recipe;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("RecipeRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@Commit
public class RecipeFunctionTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    RecipeRepository repository;

    final String givenContent = "맛있어요!";

    Recipe givenRecipe() {

        final RecipeInformation givenRecipeInformation = RecipeInformation.create(
                RecipeServingCount.valueOf("ONE")
                , RecipeCookingTime.valueOf("FIVE_MINUTES_LESS")
                , RecipeDifficultyLevel.valueOf("EASY")
        );

        Recipe givenRecipe = Recipe.create(
                1L
                , "제목"
                , "레시피상세"
                , null
                , givenRecipeInformation
                , null
                , "팁"
                , List.of(
                        RecipeStep.create("step1", null, 1),
                        RecipeStep.create("step2", null, 2),
                        RecipeStep.create("step3", null, 3)
                )
                , List.of(
                        RecipeTag.create("tag1", 0),
                        RecipeTag.create("tag2", 1)
                )
                , RecipeStatus.valueOf("IN_PROGRESS")
                , 'N'
        );
        return givenRecipe;
    }

    @Test
    @DisplayName("레시피를 생성 후 검증한다.")
    Long createRecipe() {

        Recipe givenRecipe = givenRecipe();

        Recipe saved = repository.save(givenRecipe);
        RetrieveRecipeCommandResult recipe = repository.findOneByRecipeId(saved.getId());

        assertThat(recipe.getId()).isNotNull();
        assertThat(recipe.getMemberId()).isEqualTo(givenRecipe.getMemberId());
        assertThat(recipe.getTitle()).isEqualTo(givenRecipe.getTitle());
        assertThat(recipe.getDescription()).isEqualTo(givenRecipe.getDescription());
        assertThat(recipe.getVideoFileId()).isEqualTo(givenRecipe.getVideoFileId());
        assertThat(recipe.getCompletionPhotoFileId()).isEqualTo(givenRecipe.getCompletionPhotoFileId());
        assertThat(recipe.getTip()).isEqualTo(givenRecipe.getTip());
        assertThat(recipe.getRecipeSteps().size()).isEqualTo(3);
        assertThat(recipe.getRecipeTags().size()).isEqualTo(2);
        assertThat(recipe.getStatus()).isEqualTo(givenRecipe.getStatus());

        return saved.getId();
    }

    @Test
    @DisplayName("레시피를 삭제 후 검증한다.")
    void deleteRecipe() {

        Long recipeId = createRecipe();
        long result = repository.deleteRecipe(recipeId);
        RetrieveRecipeCommandResult recipe = repository.findOneByRecipeId(recipeId);

        assertThat(recipe.getDeleteAt()).isEqualTo('Y');
    }

    @Test
    @DisplayName("레시피를 수정 후 검증한다.")
    void updateRecipe() {

        Long recipeId = createRecipe();

        Recipe recipe = entityManager.find(Recipe.class, recipeId);

        recipe.builder()
              .memberId(1L)
              .title("정선우정선우")
              .description("1")
              .videoFileId(1L)
              .completionPhotoFileId(1L)
              .tip("1")
              .deleteAt('N')
              .build();

        assertThat(recipe.getTitle()).isEqualTo("정선우정선우");

    }

    @Test
    @DisplayName("테스트")
    void test() {

    }
}