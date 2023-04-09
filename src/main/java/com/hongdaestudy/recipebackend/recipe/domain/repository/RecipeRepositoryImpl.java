package com.hongdaestudy.recipebackend.recipe.domain.repository;

import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hongdaestudy.recipebackend.recipe.domain.QRecipe.recipe;
import static com.hongdaestudy.recipebackend.recipe.domain.QRecipeStep.recipeStep;
import static com.hongdaestudy.recipebackend.recipe.domain.QRecipeTag.recipeTag;
import static com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus.IN_PROGRESS;
import static com.hongdaestudy.recipebackend.recipe.domain.RecipeStatus.PUBLISHED;
import static com.hongdaestudy.recipebackend.user.domain.QUserProfile.userProfile;

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
        , recipe.createdAt
        , recipe.updatedAt
        , recipe.memberId
        , recipe.recipeSteps
        , recipe.title
        , recipe.description
        , recipe.videoUrl
        , recipe.information.servingCount
        , recipe.information.cookingTime
        , recipe.information.difficultyLevel
        , recipe.mainPhotoFileId
        , recipe.completionPhotoFileId
        , recipe.tip
        , recipe.recipeTags
        , recipe.status
        , recipe.deleteAt
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

  @Override
  public List<RetrieveRecipeCommandResult> findAllNotDeletedRecipesById(RetrieveRecipeCommand retrieveRecipeCommand) {
    List<RetrieveRecipeCommandResult> recipeEntity = queryFactory
        .select(Projections.constructor(RetrieveRecipeCommandResult.class
          , recipe.id
          , recipe.completionPhotoFileId
          , recipe.mainPhotoFileId
          , recipe.memberId
          , recipe.title
          , userProfile.nickname.prepend("by ")))
        .from(recipe).innerJoin(userProfile).on(recipe.memberId.eq(userProfile.userId))
        .where(eqMemberId(retrieveRecipeCommand.getMemberId()),
               eqStatus(retrieveRecipeCommand.getStatus()),
               likeTitle(retrieveRecipeCommand.getTitle()),
               recipe.deleteAt.eq('N'))
        .fetch();
    return recipeEntity;
  }

  private BooleanExpression likeTitle(String title) {
    if (title != null && !title.isEmpty()) {
      return recipe.title.contains(title);
    }
    return null;
  }

  private BooleanExpression eqStatus(RecipeStatus status) {
    if (status != null && status.equals(PUBLISHED)) {
      return recipe.status.eq(PUBLISHED);
    } else if (status != null && status.equals(IN_PROGRESS)) {
      return recipe.status.eq(IN_PROGRESS);
    } else {
      return null;
    }
  }

  private BooleanExpression eqMemberId(Long memberId) {
    if (memberId != null) {
      return recipe.memberId.eq(memberId);
    }
    return null;
  }
}