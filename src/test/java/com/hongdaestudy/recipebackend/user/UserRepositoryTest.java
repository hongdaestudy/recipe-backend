package com.hongdaestudy.recipebackend.user;


import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void findAllUser() {
    UserInfoCommand userInfoCommand = new UserInfoCommand();

    List<UserInfoCommandResult> userInfoCommandResults = userRepository.findAll(userInfoCommand);

    for (UserInfoCommandResult vo : userInfoCommandResults) {
      System.out.println("vo = " + vo);
    }


  }
}
