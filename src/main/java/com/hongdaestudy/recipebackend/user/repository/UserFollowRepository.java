package com.hongdaestudy.recipebackend.user.repository;

import com.hongdaestudy.recipebackend.user.domain.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long> {

    @Query("select uf from UserFollow uf where uf.deleteYn = 'N' and uf.follower.id = :followerId and uf.following.id = :followingId")
    Optional<UserFollow> findByFollow(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

    @Query("select count(*) from UserFollow uf where uf.deleteYn = 'N' and uf.follower.id = :followerId and uf.following.id = :followingId")
    int countUserFollow(@Param("followerId") Long followerId, @Param("followingId") Long followingId);
}
