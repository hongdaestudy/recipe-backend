package com.hongdaestudy.recipebackend.user.presentation;

import com.hongdaestudy.recipebackend.user.application.EmailSenderService;
import com.hongdaestudy.recipebackend.user.application.in.EmailCodeCommand;
import com.hongdaestudy.recipebackend.user.application.in.EmailCommand;
import com.hongdaestudy.recipebackend.user.application.out.EmailCodeResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class EmailCommandController {
    private final EmailSenderService emailSenderService;

    @PostMapping("/email-send")
    public ResponseEntity<EmailCodeResult> sendEmail(@RequestBody EmailCommand request) {
        return ResponseEntity.ok(emailSenderService.sendEmailMessage(request));
    }

    @PostMapping("/verify-code")
    public ResponseEntity<Boolean> verifyCode(@RequestBody EmailCodeCommand request) {
        return ResponseEntity.ok(emailSenderService.verifyEmail(request));
    }
}
