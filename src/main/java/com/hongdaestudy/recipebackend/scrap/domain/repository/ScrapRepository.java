package com.hongdaestudy.recipebackend.scrap.domain.repository;

import com.hongdaestudy.recipebackend.scrap.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

  boolean existsByRecipeIdAndUserID(Long recipeId, Long userId);

}