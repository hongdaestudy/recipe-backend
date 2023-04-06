package com.hongdaestudy.recipebackend.recipe.repository;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.config.error.ErrorCode;
import com.hongdaestudy.recipebackend.recipe.application.ModifyRecipeService;
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
import javax.persistence.EntityTransaction;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
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

    public Long createRecipe() {
		RecipeInformation givenRecipeInformation = RecipeInformation.create(
				RecipeServingCount.valueOf("ONE")
				, RecipeCookingTime.valueOf("FIVE_MINUTES_LESS")
				, RecipeDifficultyLevel.valueOf("EASY")
		);

		Recipe givenRecipe = Recipe.create(
				1L
				, "제목"
				, givenRecipeInformation
				, 1L
				, 1L
				, RecipeStatus.valueOf("IN_PROGRESS")
				, "팁"
				, "제목"
				, "videoUrl"
				, List.of(
						RecipeStep.create("step1", null, 1),
						RecipeStep.create("step2", null, 2),
						RecipeStep.create("step3", null, 3)
				)
				, List.of(
						RecipeTag.create("tag1", 0),
						RecipeTag.create("tag2", 1)
				)
				, 'N'
		);
        Recipe saved = repository.save(givenRecipe);
		return saved.getId();
    }

	@Test
	@DisplayName("레시피를 삭제 후 검증한다.")
	void deleteRecipe() throws Exception {
		Long recipeId = createRecipe();

		Recipe saved = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));

		saved.delete();
		Recipe saved2 = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));

		assertThat(saved.getDeleteAt()).isEqualTo('Y');
	}

	@Test
	@DisplayName("레시피를 수정 후 검증한다.")
	void updateRecipe2() throws Exception {

		Long recipeId = createRecipe();

		Recipe recipe = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));
		//recipe.updateRecipeInfo(1L, "설명", 1L, RecipeStatus.IN_PROGRESS, "팁", "타이틀", "URL");

		Recipe recipe2 = repository.findById(recipeId).orElseThrow(() -> new Exception(ErrorCode.MEMBER_NOT_FOUND.getMessage()));

		assertThat(recipe2.getCompletionPhotoFileId()).isEqualTo(1L);
		assertThat(recipe2.getDescription()).isEqualTo("설명");
		assertThat(recipe2.getMainPhotoFileId()).isEqualTo(1L);
		assertThat(recipe2.getStatus()).isEqualTo(RecipeStatus.IN_PROGRESS);
		assertThat(recipe2.getTip()).isEqualTo("팁");
		assertThat(recipe2.getTitle()).isEqualTo("타이틀");
		assertThat(recipe2.getVideoUrl()).isEqualTo("URL");
	}

	@Test
	void findByRecipeId() {

	}
}