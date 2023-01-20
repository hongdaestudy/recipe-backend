package com.hongdaestudy.recipebackend.file.application;


import com.hongdaestudy.recipebackend.file.domain.Files;
import com.hongdaestudy.recipebackend.file.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterFileService {
  private final FileRepository fileRepository;


  @Transactional
  public long uploadFile(MultipartFile uploadFile) throws Exception {
    String originalFileName = uploadFile.getOriginalFilename();    //오리지날 파일명
    String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
    String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명

    Files fileInfo = Files.create(originalFileName
        , extension, "/Users/zion/IdeaProjects/upload/", uploadFile.getSize()
    );
    File destFile = new File("/Users/zion/IdeaProjects/upload/" + savedFileName);
    uploadFile.transferTo(destFile);

    return fileRepository.save(fileInfo).getId();

  }

  @Transactional
  public List<Long> uploadFiles(MultipartFile[] files) throws Exception {
    List<Long> savedList = new ArrayList<>();
    for (MultipartFile file : files) {
      String originalFileName = file.getOriginalFilename();    //오리지날 파일명
      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
      String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명

      //TODO 디렉토리 구조
      Files fileInfo = Files.create(originalFileName
          , extension, "/Users/zion/IdeaProjects/upload/", file.getSize()
      );
      File saveFile = new File("/Users/zion/IdeaProjects/upload/" + savedFileName);
      file.transferTo(saveFile);

      savedList.add(fileRepository.save(fileInfo).getId());
    }
    return savedList;
  }
}
