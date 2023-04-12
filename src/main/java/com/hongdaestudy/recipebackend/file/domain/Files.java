package com.hongdaestudy.recipebackend.file.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "file")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Files extends BaseTimeEntity {

  //TODO 테이블 수정 - file_cnt, type,,,
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String fileName;

  private String originalName;

  @Column(name = "file_ext")
  private String extension;

  private String filePath;

  private long fileSize;

  private Integer order;
  public static Files create(String originalName, String extension, String filePath, long fileSize,Integer order) {

    Files file = new Files();
    file.fileName = originalName;
    file.originalName = originalName;
    file.extension = extension;
    file.filePath = filePath;
    file.fileSize = fileSize;

    file.order = order;

    return file;
  }
  public static Files create(Long id , String originalName, String extension, String filePath, long fileSize,Integer order) {

    Files file = new Files();
    file.id = id;
    file.fileName = originalName;
    file.originalName = originalName;
    file.extension = extension;
    file.filePath = filePath;
    file.fileSize = fileSize;

    file.order = order;

    return file;
  }
}