package com.hongdaestudy.recipebackend.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreatedEvent {
    User user;

    public UserCreatedEvent(User user){
        this.user = user;
    }
}
