package com.hongdaestudy.recipebackend.file.application;


import com.hongdaestudy.recipebackend.file.domain.Files;
import com.hongdaestudy.recipebackend.file.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RetrieveFileService {
  private final FileRepository fileRepository;

  @Transactional
  public Files retrieveFile(Long fileId) throws Exception {
    return fileRepository.findById(fileId).orElse(null);
  }
}
