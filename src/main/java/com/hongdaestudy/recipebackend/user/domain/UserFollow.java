package com.hongdaestudy.recipebackend.user.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserFollow extends BaseTimeEntity<UserFollow, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_follow_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private User followingId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private User followerId;

    @ColumnDefault("N")
    private String deleteYn;

    @Builder
    public UserFollow(Long id, User followingId, User followerId, String deleteYn) {
        this.id = id;
        this.followingId = followingId;
        this.followerId = followerId;
        this.deleteYn = deleteYn;
    }
}
