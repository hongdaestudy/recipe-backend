package com.hongdaestudy.recipebackend.user.application;

import com.hongdaestudy.recipebackend.config.security.JwtTokenGenerator;
import com.hongdaestudy.recipebackend.user.application.in.UserRegisterCommand;
import com.hongdaestudy.recipebackend.user.application.out.UserRegisterCommandResult;
import com.hongdaestudy.recipebackend.user.domain.*;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenGenerator tokenGenerator;

    @Transactional
    public UserRegisterCommandResult create(UserRegisterCommand request) {
        String password = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getEmail(), password);
        userRepository.save(user);
        user = userRepository.findById(user.getId()).orElseThrow();
        return UserRegisterCommandResult.from(user, tokenGenerator.create(user, user.getUserProfile()));
    }
}
