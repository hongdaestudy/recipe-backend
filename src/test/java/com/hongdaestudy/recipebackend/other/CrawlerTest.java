package com.hongdaestudy.recipebackend.other;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import com.hongdaestudy.recipebackend.config.TestConfig;
import com.hongdaestudy.recipebackend.ingredient.application.in.RegisterIngredientGroupCommand;
import com.hongdaestudy.recipebackend.recipe.application.in.RegisterRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.domain.*;
import com.hongdaestudy.recipebackend.recipe.domain.repository.RecipeRepository;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.domain.User;
import com.hongdaestudy.recipebackend.user.domain.UserProfile;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:/application-test.yml")
@AutoConfigureTestDatabase(replace = NONE)
@Import(TestConfig.class)
@Slf4j
public class CrawlerTest {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private RecipeRepository repository;

  @Autowired
  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private static List<String> recipes = new ArrayList<>();

  @Test
  @Transactional
  @Rollback(value = false)
  @DisplayName("쉐프의 정보를 크롤링 후 DB에 입력한다.")
  void chefDatacrawlingAndDBInsert() throws IOException {
    log.info("크롤링 시작!");
    String URL = "https://www.10000recipe.com/chef/chef_list.html";
    Document doc = Jsoup.connect(URL).get();
    Elements elements = doc.select("div.chef_list4_in > div.list_lump");
    String password = passwordEncoder.encode("12345678");

    Long count = 0L;

    for (Element element : elements) {
      count++;
      String num = element.select("p.list_ranking2_num").text(); // 순위
      String email = element.select("div.list_mem3 > a.mem_pic").attr("href").replace("/profile/index.html?uid=", "") + "@gmail.com"; // 이메일
      String srcPath = element.select("div.list_mem3 > a.mem_pic > img ").attr("src"); // 사진
      String registeredRecipeCnt = element.select("div.list_cont4 > span.mem_cont1").text(); // 등록한 레시피
      String scrapCnt = element.select("div.list_cont4 > span.mem_cont3").text(); // 스크랩 수
      String recipeHits = element.select("div.list_cont4 > span.mem_cont7").text(); // 레시피 조회수
      String newsCnt = element.select("div.list_cont4 > span.mem_cont2").text(); // 소식받는 수
      String nickname = element.select("a.info_name_f").text(); // 닉네임

      UserProfile userProfile = UserProfile.builder()
          .nickname(nickname)
          .profileFileId(null)
          .backgroundFileId(null)
          .build();

      em.persist(userProfile);

      User user = User.builder()
          .userProfile(userProfile)
          .email(email)
          .password(password)
          .build();
      em.persist(user);
      em.persist(userProfile);
    }
  }

  @Test
  @Transactional
  @Rollback(value = false)
  @DisplayName("레시피 정보를 크롤링 후 DB에 입력한다.")
  void RecipesDatacrawlingAndDBInsert() throws IOException {

    searchURL();

    List<RecipeStep> recipeSteps = new ArrayList<>();
    List<RecipeTag> recipeTags = new ArrayList<>();

    for (String URL : recipes) {
      Document doc = Jsoup.connect(URL).get();
      Elements elements = doc.select("div.col-xs-9");

      for (Element element : elements) {
        String email = element.select("div.user_info2 > a").attr("href").replace("/profile/index.html?uid=", "") + "@gmail.com";
        String title = element.select("div.view2_summary.st3 > h3").text();
        String content = element.select("div.view2_summary_in").text();

        String serving = element.select("span.view2_summary_info1").text();
        String cookingTime = element.select("span.view2_summary_info2").text();
        String difficulty = element.select("span.view2_summary_info3").text();
        String ingredient;
        String unit;
        String ingredientSort;

        Elements ingredirents = element.select("div.ready_ingre3 > ul");
        for (Element ul : ingredirents) {
          ingredientSort = ul.select("b").text();
          Elements li = ul.select("li");
          int count = 0;
          for (Element a : li) {
            count++;
            ingredient = a.select("a").text();
            unit = a.select("span.ingre_unit").text();

            log.info("ingredient = {}, unit = {}, ingredientSort = {}", ingredient, unit, ingredientSort);
          }
        }

        String tip = null;
        Elements tips = element.select("dl.view_step_tip > dd");
        if (!tips.isEmpty()) {
          for (Element eleTip : tips) {
            tip = eleTip.text();
          }
        } else {
          tip = "미작성";
        }

        Elements divs = element.select("div.view_step > div[id^=stepDiv]");
        int count = 0;
        for (Element div : divs) {
          count++;
          log.info("step={} = {}", count, div.select("div.media-body").text());
          RecipeStep recipeStep = RecipeStep.create(div.select("div.media-body").text(),
                                                    null,
                                                    count);
          recipeSteps.add(recipeStep);
        }

        Elements tags = element.select("div.view_tag > a");
        int tagOrder = 0;
        for (Element tag : tags) {
          tagOrder++;
          log.info("tag = {}", tag.text());
          RecipeTag recipeTag = RecipeTag.create(tag.text(), tagOrder);
          recipeTags.add(recipeTag);
        }

        log.info("email = {}, title = {}, content = {}", email, title, content);
        log.info("serving = {}, cookingTime = {}, difficulty = {} ", serving, cookingTime, difficulty);

        if (difficulty.equals("아무나") && difficulty.equals("초급")) {
          difficulty = "쉬움";
        }  else if (difficulty.equals("중급")) {
          difficulty = "중급";
        }
        RecipeServingCount servingCnt = RecipeServingCount.findByDescription(serving);
        RecipeDifficultyLevel difficultyLevel = RecipeDifficultyLevel.findByDescription(difficulty);
        RecipeCookingTime result = RecipeCookingTime.findByDescription(cookingTime);

        log.info("result = {}, servingCnt = {}, difficultyLevel = {}", result, servingCnt, difficultyLevel);

        Long userId = null;

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
          userId = user.get().getId();
        }


        RecipeInformation givenRecipeInformation =
            RecipeInformation.create(
                  RecipeServingCount.valueOf(servingCnt.name())
                , RecipeCookingTime.valueOf(result.name())
                , RecipeDifficultyLevel.valueOf(difficultyLevel.name()));

        Recipe givenRecipe = Recipe.create(
            List.of(1L,2L)
            , content
            , null
            , givenRecipeInformation
            , 1L
            , userId
            , RecipeStatus.valueOf("PUBLISHED")
            , tip
            , title
            , "videoUrl"
            , recipeSteps
            , recipeTags
            ,'N');

        repository.save(givenRecipe);


        System.out.println();
        System.out.println();
      }
    }
  }

  private LocalDateTime createRandomDate(String createDate) {
    Random random = new Random();
    LocalDate startDate;
    LocalDate endDate;

    if (createDate.equals("createdAt")) {
      startDate = LocalDate.of(2020, 1, 1); // 시작일
      endDate   = LocalDate.of(2021, 12, 31); // 종료일
    } else {
      startDate = LocalDate.of(2022, 1, 1); // 시작일
      endDate   = LocalDate.of(2023, 4, 13); // 종료일
    }

    int minDay = (int) startDate.toEpochDay();
    int maxDay = (int) endDate.toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);
    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
    LocalTime randomTime = LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60)); // 랜덤 시간
    LocalDateTime randomDateTime = LocalDateTime.of(randomDate, randomTime);
    return randomDateTime;
  }

  private static void getRecipesURL() throws IOException {
    log.info("크롤링 시작!");
    String URL = "https://www.10000recipe.com/ranking/home_new.html";
    Document doc = Jsoup.connect(URL).get();
    Elements elements = doc.select("a.common_sp_link");

    int count = 0;
    for (Element element : elements) {
      count++;
      String recipeURL = "https://www.10000recipe.com" + element.attr("href");
      recipes.add(recipeURL);
    }
  }

  @Test
  void searchURL() throws IOException {
    String URL = "https://www.10000recipe.com/chef/chef_list.html";
    Document doc = Jsoup.connect(URL).get();
    Elements b = doc.select("div.list_cont4 > b");

    for (Element element : b) {
      String url = "https://www.10000recipe.com/" + element.select("a").attr("href");

      Document doc2 = Jsoup.connect(url).get();
      String link = "https://www.10000recipe.com" + doc2.select("ul.cont_list.st3 > li:first-child > a").attr("href");
      recipes.add(link);
    }
  }

  @Test
  @Transactional
  @Rollback(value = false)
  @DisplayName("레시피 정보를 크롤링 후 DB에 입력한다.")
  void insertRecipe() {
    RecipeInformation givenRecipeInformation = RecipeInformation.create(
          RecipeServingCount.valueOf("ONE")
        , RecipeCookingTime.valueOf("FIVE_MINUTES_LESS")
        , RecipeDifficultyLevel.valueOf("EASY"));
    RecipeCategory givenRecipeCategory = RecipeCategory.create(
          RecipeKind.valueOf("BREAD")
        , RecipeSituation.valueOf("BOXED")
        , RecipeMethod.valueOf("BOIL")
        , RecipeIngredient.valueOf("BEEF"));
    Recipe givenRecipe = Recipe.create(
                                        List.of(1L,2L)
                                      , "제목"
                                      , givenRecipeCategory
                                      , givenRecipeInformation
                                      , 1L
                                      , 1L
                                      , RecipeStatus.valueOf("IN_PROGRESS")
                                      , "팁"
                                      , "제목"
                                      , "videoUrl"
                                      , List.of(
                                          RecipeStep.create("step1", null, 1),
                                          RecipeStep.create("step2", null, 2),
                                          RecipeStep.create("step3", null, 3))
                                      , List.of(
                                          RecipeTag.create("tag1", 0),
                                          RecipeTag.create("tag2", 1))
                                      ,'N');
    repository.save(givenRecipe);
  }

  @Test
  @DisplayName("레시피의 Tip을 조회한다.")
  void select() throws IOException {
    int count = 0;

      Document doc = Jsoup.connect("https://www.10000recipe.com/recipe/6948720").get();
      Elements elements = doc.select("dl.view_step_tip > dd");
      System.out.println("elements = " + elements.isEmpty());

      if (elements != null) {
        for (Element element : elements) {
          count++;
          log.info("tips = {}", element.text());
        }
      } else {
        log.info("element is null");
      }
    log.info("count = {}", count);
  }
}
