package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.Hibernate;

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
public class Recipe extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recipe_id")
  private Long id;

  // TODO jaesay: member 엔티티 생성되면 수정
  private Long memberId;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<RecipeStep> recipeSteps = new ArrayList<>();

  private String title;

  private String description;

  private String videoUrl;

  @Embedded
  private RecipeInformation information;
  
  private Long mainPhotoFileId;
  private Long completionPhotoFileId;

  private String tip;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<RecipeTag> recipeTags = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private RecipeStatus status;

  private char deleteAt;

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

  public static Recipe create(
      Long memberId,
      String title,
      String description,
      String videoUrl,
      RecipeInformation information,
      Long mainPhotoFileId,
      Long completionPhotoFileId,
      String tip,
      List<RecipeStep> recipeSteps,
      List<RecipeTag> recipeTags,
      RecipeStatus status,
      char deleteAt) {

    Recipe recipe = new Recipe();
    recipe.memberId = memberId;
    recipe.title = title;
    recipe.description = description;
    recipe.videoUrl = videoUrl;
    recipe.information = information;
    recipe.mainPhotoFileId = mainPhotoFileId;
    recipe.completionPhotoFileId = completionPhotoFileId;
    recipe.tip = tip;
    recipe.status = status;
    recipe.deleteAt = deleteAt;

    recipeSteps.forEach(recipe::addRecipeStep);
    recipeTags.forEach(recipe::addRecipeTag);

    return recipe;
  }

  @Builder
  public void updateRecipeInfo(String title, String description, Long videoFileId,  Long completionPhotoFileId, String tip, char deleteAt) {

      this.title = title;
      this.description = description;
      this.videoFileId = videoFileId;
      this.completionPhotoFileId = completionPhotoFileId;
      this.tip = tip;
      this.deleteAt = deleteAt;
      this.updatedAt = LocalDateTime.now();
  }

  public void delete() {
	  this.deleteAt = 'Y';
  }
}
