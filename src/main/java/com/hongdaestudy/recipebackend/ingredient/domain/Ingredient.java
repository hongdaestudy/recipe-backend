package com.hongdaestudy.recipebackend.ingredient.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "ingredient")
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Ingredient {

  @EmbeddedId
  private IngredientId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ingredient_group_id")
  @ToString.Exclude
  private IngredientGroup ingredientGroup;

  private String name;

  private String amount;

  private int sort;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

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
