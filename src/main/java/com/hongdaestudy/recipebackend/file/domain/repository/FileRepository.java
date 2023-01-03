package com.hongdaestudy.recipebackend.file.domain.repository;

import com.hongdaestudy.recipebackend.file.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long> {

}
