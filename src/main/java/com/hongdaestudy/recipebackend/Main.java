package com.hongdaestudy.recipebackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class Main {
  public static void main(String[] args) {

    String[] urls = {
        "https://10000recipe.com/recipe/6912734",
        "https://10000recipe.com/recipe/4030952",
        "https://10000recipe.com/recipe/7000070",
        "https://10000recipe.com/recipe/6843136",
        "https://10000recipe.com/recipe/7000068",
        "https://10000recipe.com/recipe/6993517",
        "https://10000recipe.com/recipe/7000067",
        "https://10000recipe.com/recipe/7000065",
        "https://10000recipe.com/recipe/6957535",
        "https://10000recipe.com/recipe/6885909",
        "https://10000recipe.com/recipe/6877253",
        "https://10000recipe.com/recipe/6897374",
        "https://10000recipe.com/recipe/6915088",
        "https://10000recipe.com/recipe/6933760",
        "https://10000recipe.com/recipe/6881815",
        "https://10000recipe.com/recipe/6878340",
        "https://10000recipe.com/recipe/6881099",
        "https://10000recipe.com/recipe/6868389",
        "https://10000recipe.com/recipe/6907091",
        "https://10000recipe.com/recipe/6989618",
        "https://10000recipe.com/recipe/6830616",
        "https://10000recipe.com/recipe/6840157",
        "https://10000recipe.com/recipe/6913952",
        "https://10000recipe.com/recipe/6991123",
        "https://10000recipe.com/recipe/6929388",
        "https://10000recipe.com/recipe/6846168",
        "https://10000recipe.com/recipe/6869489",
        "https://10000recipe.com/recipe/6951564",
        "https://10000recipe.com/recipe/6968567",
        "https://10000recipe.com/recipe/6891606",
        "https://10000recipe.com/recipe/6875304",
        "https://10000recipe.com/recipe/6933414",
        "https://10000recipe.com/recipe/6894755",
        "https://10000recipe.com/recipe/6871953",
        "https://10000recipe.com/recipe/6869017",
        "https://10000recipe.com/recipe/6892138",
        "https://10000recipe.com/recipe/6930961",
        "https://10000recipe.com/recipe/6897383",
        "https://10000recipe.com/recipe/6985504",
        "https://10000recipe.com/recipe/6922700"
    };

    // 웹 크롤링 시작
    try {
      for (int i = 0; i < urls.length; i++) {
        Document doc = Jsoup.connect(urls[i]).get();
        Elements divs = doc.select("div.view2_summary.st3"); // view2_summary st3 속성을 가진 div 선택
        for (Element div : divs) {
          Element h3 = div.selectFirst("h3"); // 해당 div 안에 있는 첫번째 h3 태그 선택
          String text = h3.text(); // h3 태그의 내용 가져오기

          Elements divElements1 = doc.select("div.view2_summary_info > span.view2_summary_info1"); // 몇인분
          Elements divElements2 = doc.select("div.view2_summary_info > span.view2_summary_info2"); // 소요시간
          Elements divElements3 = doc.select("div.view2_summary_info > span.view2_summary_info3"); // 난이도
          Element  divElements4 = doc.selectFirst("div#recipeIntro.view2_summary_in");
          Elements steps = doc.getElementsByClass("stepdescr");

          System.out.println(text);
          System.out.println(divElements1.text());
          System.out.println(divElements2.text());
          System.out.println(divElements3.text());
          System.out.println(divElements4.text());
          for (Element step : steps) {
            System.out.println(step.text());
          }
        }
        System.out.println();
        System.out.println();
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
