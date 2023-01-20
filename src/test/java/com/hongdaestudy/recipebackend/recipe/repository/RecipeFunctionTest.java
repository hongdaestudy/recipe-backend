package com.hongdaestudy.recipebackend.recipe.repository;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("RecipeRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
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
				,null
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
        assertThat(recipe.getVideoUrl()).isEqualTo(givenRecipe.getVideoUrl());
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
		System.out.println("deleteAt = " + recipe.getDeleteAt());
        assertThat(recipe.getDeleteAt()).isEqualTo('Y');
    }

	@Test
	@DisplayName("레시피를 삭제 후 검증한다.(2)")
	void deleteRecipe2() throws Exception {
		Long recipeId = createRecipe();
		Recipe entity = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		entity.delete();

		Recipe entity2 = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));

		assertThat(entity2.getDeleteAt()).isEqualTo('Y');
	}

    @Test
    @DisplayName("레시피를 수정 후 검증한다.")
    void updateRecipe() {

        Long recipeId = createRecipe();

        Recipe recipe = entityManager.find(Recipe.class, recipeId);

        recipe.builder()
              .title("정선우정선우")
              .description("1")
              .videoUrl("url")
              .completionPhotoFileId(1L)
              .tip("1")
              .deleteAt('N')
              .build();

        assertThat(recipe.getTitle()).isEqualTo("정선우정선우");

    }

	@Test
	@DisplayName("레시피를 수정 후 검증한다.(2)")
	void updateRecipe2() throws Exception {

		Long recipeId = createRecipe();

		Recipe recipe = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		recipe.updateRecipeInfo("치킨", "상위 1%의 매출을 올리는 치킨", "url",
								1234L, "A", 'N');

		Recipe recipe2 = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		assertThat(recipe2.getTitle()).isEqualTo("치킨");
		assertThat(recipe2.getDescription()).isEqualTo("상위 1%의 매출을 올리는 치킨");
		assertThat(recipe2.getVideoUrl()).isEqualTo("url");
		assertThat(recipe2.getCompletionPhotoFileId()).isEqualTo(1234L);
		assertThat(recipe2.getTip()).isEqualTo("A");
		assertThat(recipe2.getDeleteAt()).isEqualTo('N');
	}


}