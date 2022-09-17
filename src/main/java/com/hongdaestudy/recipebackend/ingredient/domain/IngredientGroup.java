package com.hongdaestudy.recipebackend.ingredient.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "ingredient_group")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class IngredientGroup extends BaseTimeEntity {

  @EmbeddedId
  private IngredientGroupId id;

  private RecipeId recipeId;

  private String name;

  private int sort;

  @OneToMany(mappedBy = "ingredientGroup", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<Ingredient> ingredients = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    IngredientGroup that = (IngredientGroup) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
    ingredient.setIngredientGroup(this);
  }

  public static IngredientGroup create(RecipeId recipeId, String name, int sort, List<Ingredient> ingredients) {
    IngredientGroup ingredientGroup = new IngredientGroup();
    ingredientGroup.recipeId = recipeId;
    ingredientGroup.name = name;
    ingredientGroup.sort = sort;

    ingredients.forEach(ingredientGroup::addIngredient);

    return ingredientGroup;
  }
}
