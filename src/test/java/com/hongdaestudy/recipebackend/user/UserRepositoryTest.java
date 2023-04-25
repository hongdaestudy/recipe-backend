package com.hongdaestudy.recipebackend.user;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.hongdaestudy.recipebackend.user.domain.User;
import com.hongdaestudy.recipebackend.user.domain.UserFollow;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

  @Autowired
  private TestEntityManager testEntityManager;

  private EntityManager em;


  @BeforeEach
  void init() {
    em = testEntityManager.getEntityManager();
  }

  @Test
  @DisplayName("모든 유저를 조회한다.")
  void findAllUser() {
    UserInfoCommand DTO = UserInfoCommand.builder()
        .nickname("시크")
        .build();

//    List<UserInfoCommandResult> userInfoCommandResults = userRepository.findAll(DTO, null);
//    for (UserInfoCommandResult vo : userInfoCommandResults) {
//      System.out.println("vo = " + vo);
//    }
  }

  @Test
  @DisplayName("유저가 작성한 레시피를 불러오고 검색조건이 있으면 like 조건을 추가한다.")
  void findNotDeletedRecipesById() {
    RetrieveRecipeCommand retrieveRecipeCommand = new RetrieveRecipeCommand();
    PageRequest pageRequest = PageRequest.of(0, 10);
    Page<RetrieveRecipeCommandResult> results = recipeRepository.findAllNotDeletedRecipesById(retrieveRecipeCommand, pageRequest);
    for (RetrieveRecipeCommandResult result : results) {
      System.out.println(result);
    }
  }

  @Test
  @DisplayName("페이징 기능을 테스트한다.")
  void testPaging() {
    PageRequest pageRequest = PageRequest.of(0, 10);
    UserInfoCommand dto = UserInfoCommand.builder().build();
    Page<UserInfoCommandResult> results = userRepository.findAll(dto, pageRequest);
    System.out.println("total size = " + results.getTotalElements());
    System.out.println("total pages = " + results.getTotalPages());
    for (UserInfoCommandResult result : results) {
      System.out.println("result = " + result);
    }
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void selectFollow() {

    User user1 = User.builder()
                     .userProfile(null)
                     .email("laborlawseon@gmail.com")
                     .password("12345")
                     .build();

    User user2 = User.builder()
                     .userProfile(null)
                     .email("seonwoo@gmail.com")
                     .password("12345")
                     .build();

    EntityTransaction tx = em.getTransaction();

    em.persist(user1);
    em.persist(user2);

    UserFollow userFollow = UserFollow.builder()
                                      .followingId(user1)
                                      .followerId(user2)
                                      .build();
    em.persist(userFollow);
    tx.commit();
  }
}
