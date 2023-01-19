package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeRequestDto {

	private LocalDateTime localDateTime;
	private Long completionPhotoFileId;
	private String description;
	private String tip;
	private String title;
	private Long videoFileId;
	private char deleteAt;
}
