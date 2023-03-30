package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChefRepository extends JpaRepository<User, Long> {
    List<User> findAllById(Long id);
}
