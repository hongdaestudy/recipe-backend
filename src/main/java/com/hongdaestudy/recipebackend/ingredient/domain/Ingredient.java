package com.hongdaestudy.recipebackend.ingredient.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "ingredient")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Ingredient extends BaseTimeEntity {

  @EmbeddedId
  private IngredientId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ingredient_group_id")
  @ToString.Exclude
  private IngredientGroup ingredientGroup;

  private String name;

  private String amount;

  private int sort;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Ingredient that = (Ingredient) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  void setIngredientGroup(IngredientGroup ingredientGroup) {
    this.ingredientGroup = ingredientGroup;
  }

  public static Ingredient create(String name, String amount, int sort) {
    Ingredient ingredient = new Ingredient();
    ingredient.name = name;
    ingredient.amount = amount;
    ingredient.sort = sort;

    return ingredient;
  }
}
