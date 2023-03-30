package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.user.application.ChefService;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chef")
@RequiredArgsConstructor
@Slf4j
public class ChefCommandController {

    private final ChefService chefService;

    // 쉐프 전체 조회
    // url: localhost:8080/chef/chef_list
    // 정렬 기준 소식받기 순위 / 최근활동쉐프 / 새로운쉐프 / 쉐프닉네임
    // DDD에서 Restful API도 괜찮은지..?
    @GetMapping("/chef_list")
    public ResponseEntity<List<User>> findAll(UserInfoCommand params) {
        return ResponseEntity.ok(chefService.findAll(params));
    }

    // 해당 쉐프가 작성한 모든 레시피 조회
    // 정렬 기준이 무엇인지 ?
    @GetMapping("/chef_list/{id}")
    public ResponseEntity<List<User>> findAllById(@PathVariable Long id) {
        log.info("findAllById Method parameter id => {}", id);
        return ResponseEntity.ok(chefService.findAllById(id));
    }
}
