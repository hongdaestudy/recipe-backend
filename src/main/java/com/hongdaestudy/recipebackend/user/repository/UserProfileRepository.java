package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.concurrent.ThreadLocalRandom;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
