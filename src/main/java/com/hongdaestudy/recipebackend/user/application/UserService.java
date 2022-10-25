package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.config.security.JwtTokenGenerator;
import com.hongdaestudy.recipebackend.config.security.JwtTokenProvider;
import com.hongdaestudy.recipebackend.user.application.in.UserLoginCommand;
import com.hongdaestudy.recipebackend.user.application.in.UserRegisterCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserRegisterCommandResult;
import com.hongdaestudy.recipebackend.user.domain.*;
import com.hongdaestudy.recipebackend.user.repository.BearerTokenRepository;
import com.hongdaestudy.recipebackend.user.repository.UserProfileRepository;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final JwtTokenGenerator tokenGenerator;
    private final JwtTokenProvider tokenProvider;
    private final BearerTokenRepository bearerTokenRepository;

    @Transactional
    public UserRegisterCommandResult create(UserRegisterCommand request) {
        String password = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getEmail(), password);
        userRepository.save(user);
        user = userRepository.findById(user.getId()).orElseThrow();
        return UserRegisterCommandResult.from(user, tokenGenerator.create(user, user.getUserProfile()));
    }

    @Transactional
    public Tokens login(UserLoginCommand userLoginCommand) {
        return userRepository.findByEmail(userLoginCommand.getEmail())
                .filter(user -> passwordEncoder.matches(userLoginCommand.getPassword(), user.getPassword()))
                .map(user -> tokenGenerator.create(user, user.getUserProfile()))
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호를 잘못 입력하셨습니다."));
    }
}
