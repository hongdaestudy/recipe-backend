package com.hongdaestudy.recipebackend.recipe.repository;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

import static com.hongdaestudy.recipebackend.recipe.domain.QRecipe.recipe;

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

    @Autowired
    private ApplicationContext ac;

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
        );
        return givenRecipe;
    }

    @Test
    @DisplayName("레시피를 생성 후 검증한다.")
    void createRecipe() {

        Recipe givenRecipe = givenRecipe();

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

    @Test
    void test() {
        Projections.constructor(RetrieveRecipeCommandResult.class
                , recipe.id
                , recipe.memberId
                //, recipe.recipeSteps
                , recipe.title
                , recipe.description
                , recipe.videoFileId
                , recipe.information.servingCount
                , recipe.information.cookingTime
                , recipe.information.difficultyLevel
                , recipe.completionPhotoFileId
                , recipe.tip
                //, recipe.recipeTags
                , recipe.status
        );
    }

    @Test
    void searchBean() {
        String[] beans = ac.getBeanDefinitionNames();

        for (String bean : beans) {
            System.out.println("bean = " + bean);
        }
    }
}