package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.recipe.application.in.RetrieveRecipeCommand;
import com.hongdaestudy.recipebackend.recipe.application.out.RetrieveRecipeCommandResult;
import com.hongdaestudy.recipebackend.user.application.UserInfoService;
import com.hongdaestudy.recipebackend.user.application.in.UserInfoCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserInfoCommandResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chef")
@RequiredArgsConstructor
@Slf4j
public class UserInfoCommandController {

    private final UserInfoService userInfoService;

    // 쉐프 전체 조회 및 닉네임으로 조회
    // todo: 소식받기 순위, 최근활동쉐프, 새로운쉐프, 내가 소식받는쉐프 조건 추가 필요
    @GetMapping("/chef_list")
    public ResponseEntity<Page<UserInfoCommandResult>> findAllByCondition(UserInfoCommand params, Pageable pageable) {
        return ResponseEntity.ok(userInfoService.findAll(params, pageable));
    }

    // 해당 쉐프가 작성한 모든 레시피 조회, 레시피 제목으로 검색
    @GetMapping("/recipes")
    public ResponseEntity<Page<RetrieveRecipeCommandResult>> findAllByRecipesByCondition(RetrieveRecipeCommand params, Pageable pageable) {
        return ResponseEntity.ok(userInfoService.findAllNotDeletedRecipesById(params, pageable));
    }

    @PostMapping("/following")
    public ResponseEntity<UserInfoCommandResult> registerFollow(UserInfoCommand params) {
        return ResponseEntity.ok(userInfoService.registerFollow(params));
    }

    @GetMapping("/following")
    public ResponseEntity<Page<RetrieveRecipeCommandResult>> findAllRecipesByFollowingChef(RetrieveRecipeCommand params, Pageable pageable) {
        // return ResponseEntity.ok(userInfoService.findAllRecipesByFollowingChef);
        return null;
    }
}
