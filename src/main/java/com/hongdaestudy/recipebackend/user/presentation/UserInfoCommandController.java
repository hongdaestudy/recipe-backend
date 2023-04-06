package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.user.application.UserInfoService;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
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
public class UserInfoCommandController {

    private final UserInfoService userInfoService;

    // 쉐프 전체 조회
    // url: localhost:8080/chef/chef_list
    // 정렬 기준 소식받기 순위 / 최근활동쉐프 / 새로운쉐프 / 쉐프닉네임
    // DDD에서 Restful API도 괜찮은지..?
    @GetMapping("/chef_list")
    public ResponseEntity<List<UserInfoCommandResult>> findAll(UserInfoCommand params) {
        return ResponseEntity.ok(userInfoService.findAll(params));
    }

    // 해당 쉐프가 작성한 모든 레시피 조회
    // 정렬 기준이 무엇인지 ?
}
