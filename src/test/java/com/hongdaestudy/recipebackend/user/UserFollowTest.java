package com.hongdaestudy.recipebackend.user;

import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.user.domain.User;
import com.hongdaestudy.recipebackend.user.domain.UserFollow;
import com.hongdaestudy.recipebackend.user.domain.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class UserFollowTest {

  @Autowired
  private TestEntityManager testEntityManager;

  private EntityManager em;

  @BeforeEach
  void init() {
    em = testEntityManager.getEntityManager();
  }

  @Test
  @Transactional
  @Rollback(value = false)
  void followTest() {
    User following = User.builder().email("following2@naver.com").password("12345678").build();
    User follower = User.builder().email("follower2@naver.com").password("12345678").build();

    UserProfile userProfile1 = UserProfile.builder().userId(following).nickname("following2").build();
    UserProfile userProfile2 = UserProfile.builder().userId(follower).nickname("follower2").build();

    UserFollow userFollow = UserFollow.builder()
                                      .follower(follower)
                                      .following(following)
                                      .build();

    em.persist(userProfile1);
    em.persist(userProfile2);
    em.persist(following);
    em.persist(follower);
    em.persist(userFollow);

    em.flush();
    em.clear();

    em.find(UserFollow.class, userFollow.getId());

  }
}
