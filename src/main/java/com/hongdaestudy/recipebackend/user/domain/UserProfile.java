package com.hongdaestudy.recipebackend.user.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile extends BaseTimeEntity<UserProfile, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    private String nickname;

    private Long profileFileId;

    private Long backgroundFileId;

    public UserProfile(User user) {
        this.user = user;
    }

    @Builder
    public UserProfile(Long id, User userId, String nickname, Long profileFileId, Long backgroundFileId) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.profileFileId = profileFileId;
        this.backgroundFileId = backgroundFileId;
    }
}
