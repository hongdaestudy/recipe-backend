package com.hongdaestudy.recipebackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main2 {

  public static void main(String[] args) {
    String url = "https://10000recipe.com/recipe/6912734";

    try {
      Document doc = Jsoup.connect(url).get();

      Element viewStep = doc.select("div.view_step").first();
      Elements mediaBodies = viewStep.select("div.media-body");
      for (Element mediaBody : mediaBodies) {
        System.out.println(mediaBody.text());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
