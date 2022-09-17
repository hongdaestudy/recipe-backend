package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "recipe_step")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RecipeStep extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recipe_step_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipe_id")
  @ToString.Exclude
  private Recipe recipe;

  private String description;

  private String photoUrl;

  private int sort;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RecipeStep that = (RecipeStep) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public static RecipeStep create(String description, String photoUrl, int sort) {
    RecipeStep recipeStep = new RecipeStep();
    recipeStep.description = description;
    recipeStep.photoUrl = photoUrl;
    recipeStep.sort = sort;

    return recipeStep;
  }
}
