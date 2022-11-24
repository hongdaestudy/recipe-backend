package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.user.application.UserService;
import com.hongdaestudy.recipebackend.user.application.in.RefreshTokenCommand;
import com.hongdaestudy.recipebackend.user.application.in.UserLoginCommand;
import com.hongdaestudy.recipebackend.user.application.in.UserRegisterCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserRegisterCommandResult;
import com.hongdaestudy.recipebackend.user.domain.Tokens;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserCommandController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserRegisterCommandResult> create(@RequestBody UserRegisterCommand request) {
        return ResponseEntity.ok(userService.create(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@RequestBody UserLoginCommand userLoginCommand) {
        return ResponseEntity.ok(userService.login(userLoginCommand));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Tokens> refresh(@RequestBody RefreshTokenCommand refreshTokenCommand) {
        return ResponseEntity.ok(userService.refresh(refreshTokenCommand.getRefreshToken()));
    }

    @GetMapping("/email-check")
    public ResponseEntity<Boolean> isAvailableEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.isAvailableEmail(email));
    }
}
