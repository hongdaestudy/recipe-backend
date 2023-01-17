package com.hongdaestudy.recipebackend.recipe.repository;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
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
@DisplayName("RecipeRepository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class RecipeRepositoryTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	RecipeRepository repository;
	final String givenContent = "맛있어요!";

	@Nested
	@DisplayName("retrieve 메소드")
	class Describe_retrieve {

		@BeforeEach
		void prepare() {
			repository.deleteAll();
		}

		@Nested
		@DisplayName("레시피 객체가 주어지면")
		class Context_with_a_comment {
			final RecipeInformation givenRecipeInformation = RecipeInformation.create(
					RecipeServingCount.valueOf("ONE")
					, RecipeCookingTime.valueOf("FIVE_MINUTES_LESS")
					, RecipeDifficultyLevel.valueOf("EASY")
			);
			final Recipe givenRecipe = Recipe.create(
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

			@Test
			@DisplayName("주어진 객체를 저장하고, 저장된 객체를 리턴한다")
			void it_saves_obj_and_returns_a_saved_obj() {
				Recipe saved = repository.save(givenRecipe);
				RetrieveRecipeCommandResult recipe = repository.findOneByRecipeId(saved.getId());

				Assertions.assertNotNull(recipe.getId(), "저장된 객체는 아이디가 추가되어 있다");
				Assertions.assertEquals(recipe.getMemberId(), givenRecipe.getMemberId());
				Assertions.assertEquals(recipe.getTitle(), givenRecipe.getTitle());
				Assertions.assertEquals(recipe.getDescription(), givenRecipe.getDescription());
				Assertions.assertEquals(recipe.getVideoFileId(), givenRecipe.getVideoFileId());
				Assertions.assertEquals(recipe.getCompletionPhotoFileId(), givenRecipe.getCompletionPhotoFileId());
				Assertions.assertEquals(recipe.getTip(), givenRecipe.getTip());
				Assertions.assertEquals(recipe.getRecipeSteps().size(), 3);
				Assertions.assertEquals(recipe.getRecipeTags().size(), 2);
				Assertions.assertEquals(recipe.getStatus(), givenRecipe.getStatus());

			}
		}
	}
}
