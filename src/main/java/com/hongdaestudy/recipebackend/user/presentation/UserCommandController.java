package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.user.application.UserService;
import com.hongdaestudy.recipebackend.user.application.in.UserRegisterCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserRegisterCommandResult;
import com.hongdaestudy.recipebackend.user.domain.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserCommandController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserRegisterCommandResult> create(@RequestBody UserRegisterCommand request) { ;
        return ResponseEntity.ok(userService.create(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@RequestBody UserLoginCommand userLoginCommand) {
        return ResponseEntity.ok(userService.login(userLoginCommand));
    }
}
