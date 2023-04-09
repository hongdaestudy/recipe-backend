package com.hongdaestudy.recipebackend.user;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.Recipe;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(TestConfig.class)
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RecipeRepository recipeRepository;

  @Test
  @DisplayName("모든 유저를 조회한다.")
  void findAllUser() {
    UserInfoCommand DTO = UserInfoCommand.builder()
        .nickname("시크")
        .build();

    List<UserInfoCommandResult> userInfoCommandResults = userRepository.findAll(DTO);

    for (UserInfoCommandResult vo : userInfoCommandResults) {
      System.out.println("vo = " + vo);
    }
  }

  @Test
  @DisplayName("유저가 작성한 레시피를 불러오고 검색조건이 있으면 like 조건을 추가한다.")
  void findNotDeletedRecipesById() {
    RetrieveRecipeCommand retrieveRecipeCommand = new RetrieveRecipeCommand();

    retrieveRecipeCommand.setMemberId(5L);
    retrieveRecipeCommand.setTitle("");
    List<RetrieveRecipeCommandResult> results = recipeRepository.findAllNotDeletedRecipesById(retrieveRecipeCommand);
    for (RetrieveRecipeCommandResult result : results) {
      System.out.println(result);
    }
  }
}
