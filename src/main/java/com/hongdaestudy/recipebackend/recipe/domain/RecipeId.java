package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) // TODO jaesay: 잘몰라서 일단 private 으로
@Getter
@ToString
public class RecipeId implements Serializable {

  @Column(name = "recipe_id")
  private Long id;

  public static RecipeId of(Long id) {
    return new RecipeId(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RecipeId recipeId = (RecipeId) o;
    return id != null && Objects.equals(id, recipeId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
