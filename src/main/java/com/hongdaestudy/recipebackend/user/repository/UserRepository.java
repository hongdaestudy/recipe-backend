package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    @Modifying
    @Query(nativeQuery = true, value = "update user set user_profile_id = :userProfileId where user_id = :userId")
    void updateUserProfileId(Long userId, Long userProfileId);

    Optional<User> findByEmail(String email);
}
