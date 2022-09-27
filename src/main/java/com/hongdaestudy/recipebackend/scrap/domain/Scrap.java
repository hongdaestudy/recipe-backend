package com.hongdaestudy.recipebackend.scrap.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "scrap")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Scrap extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scrap_id")
  private Long id;

  @NotNull
  private Long userId;

  @NotNull
  private Long recipeId;

  @CreatedDate
  private LocalDateTime scrapDate;

  public static Scrap create(Long recipeId, Long userId) {

    Scrap scrap = new Scrap();
    scrap.recipeId = recipeId;
    scrap.userId = userId;

    return scrap;
  }
}
