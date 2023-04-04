package com.hongdaestudy.recipebackend.file.application;


import com.hongdaestudy.recipebackend.file.domain.Files;
import com.hongdaestudy.recipebackend.file.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterFileService {
  private final FileRepository fileRepository;

  @Value("${file.upload.location}")
  private String uploadDir;

  @Transactional
  public Long uploadFile(MultipartFile uploadFile) throws Exception {

    Files savedFile = null;

    if (!uploadFile.isEmpty()) {
      String originalFileName = uploadFile.getOriginalFilename();    //오리지날 파일명
      String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
      String savedFileName = UUID.randomUUID() + "_" + originalFileName;    //저장될 파일 명

      String fileDir = getFolder();
      savedFile = Files.create(originalFileName, extension, fileDir + "/", uploadFile.getSize(),0);
      uploadFile.transferTo(new File(fileDir + "/" + savedFileName));
    }
    return fileRepository.save(savedFile).getId();

  }

  @Transactional
  public List<Long> uploadFiles(MultipartFile[] files) throws Exception {
    List<Long> savedList = new ArrayList<>();

    if (files != null && files.length != 0) {
      Integer order = 0;
      for (MultipartFile uploadFile : files) {

        String originalFileName = uploadFile.getOriginalFilename();    //오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));    //파일 확장자
        String savedFileName = UUID.randomUUID() + originalFileName;    //저장될 파일 명

        String fileDir = getFolder();
        Files savedFile = Files.create(originalFileName, extension, fileDir + "/", uploadFile.getSize(),order);
        uploadFile.transferTo(new File(fileDir + "/" + savedFileName));
        savedList.add(fileRepository.save(savedFile).getId());

        order++;
      }
    }
    return savedList;
  }

  private String getFolder() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Date date = new Date();
    String str = uploadDir + "/upload/" + sdf.format(date);
    File folder = new File(str);
    if (!folder.exists()) {
      folder.mkdirs();
    }
    return str;
  }
}
