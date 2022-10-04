package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
