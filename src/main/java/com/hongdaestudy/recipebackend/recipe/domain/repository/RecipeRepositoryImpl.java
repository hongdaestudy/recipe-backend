package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hongdaestudy.recipebackend.recipe.domain.QRecipe.recipe;
import static com.hongdaestudy.recipebackend.recipe.domain.QRecipeStep.recipeStep;
import static com.hongdaestudy.recipebackend.recipe.domain.QRecipeTag.recipeTag;

@Repository
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public RecipeRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.queryFactory = jpaQueryFactory;
  }

  @Override
  public RetrieveRecipeCommandResult findOneByRecipeId(long recipeId) {

    RetrieveRecipeCommandResult recipeEntity = queryFactory.select(Projections.constructor(RetrieveRecipeCommandResult.class
        , recipe.id
        , recipe.memberId
        //, recipe.recipeSteps
        , recipe.title
        , recipe.description
        , recipe.videoUrl
        , recipe.information.servingCount
        , recipe.information.cookingTime
        , recipe.information.difficultyLevel
        , recipe.completionPhotoFileId
        , recipe.tip
        //, recipe.recipeTags
        , recipe.status
    )).from(recipe).where(recipe.id.eq(recipeId)).fetchOne();


    List<RetrieveRecipeCommandResult.RetrieveRecipeStepCommandResult> recipeSteps = queryFactory.select(Projections.constructor(RetrieveRecipeCommandResult.RetrieveRecipeStepCommandResult.class
            , recipeStep.description.as("description")
            , recipeStep.photoFileId.as("photoFileId")
            , recipeStep.sort.as("sort")
        ))
        .from(recipeStep)
        .where(recipeStep.recipe.id.eq(recipeId))
        .orderBy(recipeStep.sort.asc())
        .fetch();

    recipeSteps.forEach(recipeEntity::addRecipeStep);

    List<RetrieveRecipeCommandResult.RetrieveRecipeTagCommandResult> recipeTags = queryFactory.select(Projections.constructor(RetrieveRecipeCommandResult.RetrieveRecipeTagCommandResult.class
            , recipeTag.name.as("name")
            , recipeTag.sort.as("sort")
        ))
        .from(recipeTag)
        .where(recipeTag.recipe.id.eq(recipeId))
        .orderBy(recipeTag.sort.asc())
        .fetch();

    recipeTags.forEach(recipeEntity::addRecipeTag);

    return recipeEntity;
  }
}