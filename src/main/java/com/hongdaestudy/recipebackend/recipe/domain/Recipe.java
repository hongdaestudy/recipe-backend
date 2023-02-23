package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

  private Long completionPhotoFileId;

  private String description;

  @Embedded
  private RecipeInformation information;

  private Long mainPhotoFileId;

  // TODO jaesay: member 엔티티 생성되면 수정
  private Long memberId;

  @Enumerated(EnumType.STRING)
  private RecipeStatus status;

  private String tip;

  private String title;

  private String videoUrl;

  private char deleteAt;

  // table: recipe_tag
  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<RecipeTag> recipeTags = new ArrayList<>();

  // table: recipe_step
  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<RecipeStep> recipeSteps = new ArrayList<>();

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
          Long completionPhotoFileId,
          String description,
          RecipeInformation information,
          Long mainPhotoFileId,
          Long memberId,
          RecipeStatus status,
          String tip,
          String title,
          String videoUrl,
          List<RecipeStep> recipeSteps,
          List<RecipeTag> recipeTags,
          char deleteAt
  ) {

    Recipe recipe = new Recipe();
    recipe.completionPhotoFileId = completionPhotoFileId;
    recipe.description = description;
    recipe.information = information;
    recipe.mainPhotoFileId = mainPhotoFileId;
    recipe.memberId = memberId;
    recipe.status = status;
    recipe.tip = tip;
    recipe.title = title;
    recipe.videoUrl = videoUrl;
    recipe.deleteAt = deleteAt;

    recipeSteps.forEach(recipe::addRecipeStep);
    recipeTags.forEach(recipe::addRecipeTag);

    return recipe;
  }

  public void delete() {
    this.deleteAt = 'Y';
  }

  // 연관관계에 있는 recipe step, tag 수정은 어떻게..?
  @Builder
  public void updateRecipeInfo(RegisterRecipeCommand params) {
    if (params.getTempCompletionPhotoFileId() != null) this.completionPhotoFileId = params.getTempCompletionPhotoFileId();
    if (params.getDescription() != null) this.description = params.getDescription();
    if (params.getMainPhotoFileId() != null) this.mainPhotoFileId = params.getMainPhotoFileId();
    if (params.getStatus() != null) this.status = params.getStatus();
    if (params.getTip() != null) this.tip = params.getTip();
    if (params.getTitle() != null) this.title = params.getTitle();
    if (params.getVideoUrl() != null) this.videoUrl = params.getVideoUrl();
    if (params.getRecipeTags() != null) {

    }
    if (params.getRecipeSteps() != null) {

    }
  }
}
