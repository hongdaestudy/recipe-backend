package com.hongdaestudy.recipebackend.file;

import com.hongdaestudy.recipebackend.file.application.RegisterFileService;
import com.hongdaestudy.recipebackend.file.application.RetrieveFileService;
import com.hongdaestudy.recipebackend.file.domain.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileCommandController {

  private final RegisterFileService registerFileService;
  private final RetrieveFileService retrieveFileService;

  @GetMapping("/display")
  public ResponseEntity<Files> retrieveFile(@RequestParam("fileId") Long fileId) {
    Files result = null;
    try {
      result = retrieveFileService.retrieveFile(fileId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(result);
  }

  @PostMapping("/upload")
  public ResponseEntity<Long> registerFile(@RequestParam("file") MultipartFile file) {

    Long result = null;
    try {
      result = registerFileService.uploadFile(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(result);
  }

  @PostMapping("/uploads")
  public ResponseEntity<List<Long>> registerFiles(@RequestParam("file") MultipartFile[] files) {
    List<Long> result = null;
    try {
      result = registerFileService.uploadFiles(files);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok(result);
  }
}
