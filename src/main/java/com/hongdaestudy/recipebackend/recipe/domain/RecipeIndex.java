package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(indexName = "recipe")
@Mapping(mappingPath = "elastic/recipe-mapping.json")
public class RecipeIndex {
    /* id */
    @Id
    @Field(type = FieldType.Long)
    private Long recipeId;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private Long memberId;

    @Field(type = FieldType.Text)
    private String tip;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String servingCount;

    @Field(type = FieldType.Keyword)
    private String cookingTime;

    @Field(type = FieldType.Keyword)
    private String difficultyLevel;

    @Field(type = FieldType.Nested)
    private List<String> ingredients;

    @Field(type = FieldType.Nested)
    private List<String> tags;

    public static RecipeIndex create( Recipe recipe, List<String> ingredients) {

        RecipeIndex recipeIndex = new RecipeIndex();

        recipeIndex.recipeId = recipe.getId();
        recipeIndex.description = recipe.getDescription();
        recipeIndex.memberId = recipe.getMemberId();
        recipeIndex.tip = recipe.getTip();
        recipeIndex.title = recipe.getTitle();

        recipeIndex.servingCount = String.valueOf(recipe.getInformation().getServingCount());
        recipeIndex.cookingTime = String.valueOf(recipe.getInformation().getCookingTime());
        recipeIndex.difficultyLevel = String.valueOf(recipe.getInformation().getDifficultyLevel());
        recipeIndex.ingredients = ingredients;
        recipeIndex.tags = recipe.getRecipeTags().stream().map(x->x.getName()).collect(Collectors.toList());
        return recipeIndex;
    }

}