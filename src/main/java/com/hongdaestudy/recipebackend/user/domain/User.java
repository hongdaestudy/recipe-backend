package com.hongdaestudy.recipebackend.user.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity<User, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    private String email;

    private String password;

    // todo: validate
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        registerEvent(new UserCreatedEvent(this));
    }

    public void changeUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
