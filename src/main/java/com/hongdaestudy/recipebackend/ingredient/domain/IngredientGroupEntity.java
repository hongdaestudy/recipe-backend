package com.hongdaestudy.recipebackend.ingredient.domain;

import com.hongdaestudy.recipebackend.recipe.domain.RecipeId;
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

@Table(name = "ingredient_group")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class IngredientGroupEntity {

  @EmbeddedId
  private IngredientGroupId id;

  private RecipeId recipeId;

  private String name;

  private int sort;

  @OneToMany(mappedBy = "ingredientGroup", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<IngredientEntity> ingredients = new ArrayList<>();

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    IngredientGroupEntity that = (IngredientGroupEntity) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void addIngredient(IngredientEntity ingredient) {
    this.ingredients.add(ingredient);
    ingredient.setIngredientGroup(this);
  }
}
