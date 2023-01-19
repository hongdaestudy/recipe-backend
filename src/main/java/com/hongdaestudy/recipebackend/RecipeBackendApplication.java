package com.hongdaestudy.recipebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RecipeBackendApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(RecipeBackendApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(RecipeBackendApplication.class);
  }
}
