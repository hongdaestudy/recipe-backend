package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
@ToString
public class RecipeCategory {
  @Enumerated(EnumType.STRING)
  private RecipeKind kind;

  @Enumerated(EnumType.STRING)
  private RecipeSituation situation;

  @Enumerated(EnumType.STRING)
  private RecipeMethod method;

  @Enumerated(EnumType.STRING)
  private RecipeIngredient ingredient;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    RecipeCategory that = (RecipeCategory) o;
    return kind != null && Objects.equals(kind, that.kind)
        && situation != null && Objects.equals(situation, that.situation)
        && method != null && Objects.equals(method, that.method)
        && ingredient != null && Objects.equals(ingredient, that.ingredient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(kind, situation, method,ingredient);
  }

  public static RecipeCategory create(RecipeKind kind, RecipeSituation situation, RecipeMethod method,RecipeIngredient ingredient) {
    RecipeCategory recipeInformation = new RecipeCategory();
    recipeInformation.kind = kind;
    recipeInformation.situation = situation;
    recipeInformation.method = method;
    recipeInformation.ingredient = ingredient;
    return recipeInformation;
  }
}
