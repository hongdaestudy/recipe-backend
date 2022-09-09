package com.hongdaestudy.recipebackend.recipe.domain;

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
public class RecipeTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_step_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @ToString.Exclude
    private RecipeEntity recipe;

    private String name;

    private int sortOrder;

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

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }
}
