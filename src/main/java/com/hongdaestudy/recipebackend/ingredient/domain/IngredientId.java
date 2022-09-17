package com.hongdaestudy.recipebackend.ingredient.domain;

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
public class IngredientId implements Serializable {
  @Column(name = "ingredient_id")
  private Long value;

  public static IngredientId of(Long id) {
    return new IngredientId(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    IngredientId that = (IngredientId) o;
    return value != null && Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
