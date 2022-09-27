package com.hongdaestudy.recipebackend.user.domain;

import com.hongdaestudy.recipebackend.user.repository.UserProfileRepository;
import com.hongdaestudy.recipebackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CreateUserProfileHandler {
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @EventListener
    @Transactional
    public void createFirstUserProfile(UserCreatedEvent event) {
        User user = event.getUser();
        UserProfile userProfile = new UserProfile(user.getId());
        userProfileRepository.save(userProfile);
        user.changeUserProfile(userProfile);
        userRepository.updateUserProfileId(user.getId(), userProfile.getId());
    }
}
