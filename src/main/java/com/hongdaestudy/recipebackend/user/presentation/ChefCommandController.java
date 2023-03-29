package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.user.application.ChefService;
import com.hongdaestudy.recipebackend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chef")
@RequiredArgsConstructor
public class ChefCommandController {

    private final ChefService chefService;

    // 쉐프 전체 조회
    // url: localhost:8080/chef/chef_list
    @GetMapping("/chef_list")
    public ResponseEntity<List<User>> selectListChef() {
        return ResponseEntity.ok(chefService.selectListChef());
    }

    @GetMapping("/chef_list/{userId}")
    public ResponseEntity<List<User>> selectListChef(@PathVariable Long userId) {
        return ResponseEntity.ok(chefService.selectListChef(userId));
    }
}
