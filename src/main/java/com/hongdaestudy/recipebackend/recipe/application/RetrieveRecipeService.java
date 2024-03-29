package com.hongdaestudy.recipebackend.recipe.application;

//import com.hongdaestudy.recipebackend.ingredient.application.RetrieveIngredientGroupService;

import com.hongdaestudy.recipebackend.common.EnumMapperValue;
import com.hongdaestudy.recipebackend.ingredient.application.RetrieveIngredientGroupService;
import com.hongdaestudy.recipebackend.ingredient.application.out.RetrieveIngredientGroupCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.SearchRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCodeCommandResult;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveRecipeService {
  private final RecipeRepository recipeRepository;
  private final ElasticsearchOperations operations;
  private final RetrieveIngredientGroupService retrieveIngredientGroupService;

  @Transactional
  public RetrieveRecipeCodeCommandResult retrieveRecipeCode() {

    List<EnumMapperValue> status = Arrays.stream(RecipeStatus.values()).map(EnumMapperValue::new).collect(Collectors.toList());
    List<EnumMapperValue> cookingTime = Arrays.stream(RecipeCookingTime.values()).map(EnumMapperValue::new).collect(Collectors.toList());
    List<EnumMapperValue> difficultyLevel = Arrays.stream(RecipeDifficultyLevel.values()).map(EnumMapperValue::new).collect(Collectors.toList());
    List<EnumMapperValue> servingCount = Arrays.stream(RecipeServingCount.values()).map(EnumMapperValue::new).collect(Collectors.toList());

    List<EnumMapperValue> kind = Arrays.stream(RecipeKind.values()).map(EnumMapperValue::new).collect(Collectors.toList());
    List<EnumMapperValue> method = Arrays.stream(RecipeMethod.values()).map(EnumMapperValue::new).collect(Collectors.toList());
    List<EnumMapperValue> situation = Arrays.stream(RecipeSituation.values()).map(EnumMapperValue::new).collect(Collectors.toList());
    List<EnumMapperValue> ingredient = Arrays.stream(RecipeIngredient.values()).map(EnumMapperValue::new).collect(Collectors.toList());

    RetrieveRecipeCodeCommandResult.create(status,cookingTime,difficultyLevel,servingCount,kind,method,situation,ingredient);
    return RetrieveRecipeCodeCommandResult.create(status,cookingTime,difficultyLevel,servingCount,kind,method,situation,ingredient);
  }
  @Transactional
  public RetrieveRecipeCommandResult retrieveRecipe(RetrieveRecipeCommand retrieveRecipeCommand) {

    RetrieveRecipeCommandResult recipe = recipeRepository.findOneByRecipeId(retrieveRecipeCommand.getRecipeId());
    List<RetrieveIngredientGroupCommandResult> ingredientGroupList = retrieveIngredientGroupService.retrieveIngredientGroups(recipe.getId());
    ingredientGroupList.forEach(recipe::addIngredientGroup);

    return recipe;
  }

  @Transactional
  public List<RetrieveRecipeCommandResult> retrieveRecipeList(RetrieveRecipeCommand retrieveRecipeCommand) {

    List<RetrieveRecipeCommandResult> recipeList = recipeRepository.findAll().stream().map(recipe -> new RetrieveRecipeCommandResult(
            recipe.getId()
            , recipe.getCreatedAt()
            , recipe.getUpdatedAt()
            , recipe.getMemberId()
            //, recipe.recipeSteps
            , recipe.getTitle()
            , recipe.getDescription()
            , recipe.getVideoUrl()
            , recipe.getInformation().getServingCount()
            , recipe.getInformation().getCookingTime()
            , recipe.getInformation().getDifficultyLevel()
            , recipe.getMainPhotoFileId()
            , recipe.getCompletionPhotoFileId()
            , recipe.getTip()
            //, recipe.recipeTags
            , recipe.getStatus()
            , recipe.getDeleteAt())).collect(Collectors.toList());

    return recipeList;
  }
  public List<RecipeIndex> retrieveRecipeByCondition(SearchRecipeCommand searchCondition, Pageable pageable) {
    Query query = createConditionCriteriaQuery(searchCondition).setPageable(pageable);

    SearchHits<RecipeIndex> search = operations.search(query, RecipeIndex.class);
    return search.stream()
            .map(SearchHit::getContent)
            .collect(Collectors.toList());
  }
  private CriteriaQuery createConditionCriteriaQuery(SearchRecipeCommand searchCondition) {
    CriteriaQuery query = new CriteriaQuery(new Criteria());

    if (searchCondition == null)
      return query;

    if (searchCondition.getKind() != null)
      query.addCriteria(Criteria.where("kind").is(searchCondition.getKind()));
    if (searchCondition.getSituation() != null)
      query.addCriteria(Criteria.where("situation").is(searchCondition.getSituation()));
    if (searchCondition.getMethod() != null)
      query.addCriteria(Criteria.where("method").is(searchCondition.getMethod()));
    if (searchCondition.getIngredient() != null)
      query.addCriteria(Criteria.where("ingredient").is(searchCondition.getIngredient()));

    if (searchCondition.getCookingTime() != null)
      query.addCriteria(Criteria.where("cookingTime").is(searchCondition.getCookingTime()));
    if (searchCondition.getServingCount() != null)
      query.addCriteria(Criteria.where("servingCount").is(searchCondition.getServingCount()));
    if (searchCondition.getDifficultyLevel() != null)
      query.addCriteria(Criteria.where("difficultyLevel").is(searchCondition.getDifficultyLevel()));

    return query;
  }
  public List<String> retrieveRecipeTitlesByCondition(SearchRecipeCommand searchCondition) {
    Query query = createConditionNativeQuery(searchCondition);

    SearchHits<RecipeIndex> search = operations.search(query, RecipeIndex.class);
    return search.stream()
            .map(SearchHit::getContent)
            .map(r->r.getTitle())
            .collect(Collectors.toList());
  }
  private Query createConditionNativeQuery(SearchRecipeCommand searchCondition) {
    Query query = new NativeSearchQueryBuilder()
              .withQuery(QueryBuilders.wildcardQuery("title", "*"+searchCondition.getTitle()+"*"))
              .withSourceFilter(new FetchSourceFilter(
                      new String[]{"title"},
                      new String[]{}))
            .withMaxResults(Integer.valueOf(10))
              .build();
    return query;
  }
}
