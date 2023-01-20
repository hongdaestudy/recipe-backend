package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class RecipeRequestDto {

	private Long memberId;
	private Long recipeId;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
	private Long completionPhotoFileId;
	private String description;
	private String videoUrl;
	private String title;
	private String tip;
	private char deleteAt;
}
