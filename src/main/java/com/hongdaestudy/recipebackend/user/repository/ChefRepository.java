package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefRepository extends JpaRepository<User, Long> {

}
