package com.hongdaestudy.recipebackend.recipe.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "recipe_tag")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RecipeTag extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recipe_tag_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipe_id")
  @ToString.Exclude
  private Recipe recipe;

  private String name;

  private int sort;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RecipeTag recipeTag = (RecipeTag) o;
    return id != null && Objects.equals(id, recipeTag.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public static RecipeTag create(String name, int sort) {
    RecipeTag recipeTag = new RecipeTag();
    recipeTag.name = name;
    recipeTag.sort = sort;
    return recipeTag;
  }
}
