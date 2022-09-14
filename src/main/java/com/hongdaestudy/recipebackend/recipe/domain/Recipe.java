package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "recipe")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Recipe {

  // TODO: key 생성 전략? 은 뭔지 확인하기
  @EmbeddedId
  private RecipeId id;

  // TODO jaesay: member 엔티티 생성되면 수정
  private Long memberId;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<RecipeStep> recipeSteps = new ArrayList<>();

  private String title;

  private String description;

  private Long videoFileId;

  @Embedded
  private RecipeInformation information;

  private Long completionPhotoFileId;

  private String tip;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<RecipeTag> recipeTags = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private RecipeStatus status;

  // TODO jaesay: Auditing 으로 수정
  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Recipe that = (Recipe) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void addRecipeStep(RecipeStep recipeStep) {
    this.recipeSteps.add(recipeStep);
    recipeStep.setRecipe(this);
  }

  public void addRecipeTag(RecipeTag recipeTag) {
    this.recipeTags.add(recipeTag);
    recipeTag.setRecipe(this);
  }

  public static Recipe create() {
    return new Recipe();
  }
}
