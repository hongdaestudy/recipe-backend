package com.hongdaestudy.recipebackend.user.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"followingId", "followerId"}))
@Check(constraints = "following_id != follower_id")
@DynamicInsert
public class UserFollow extends BaseTimeEntity<UserFollow, Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_follow_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followingId", referencedColumnName = "user_id")
    private User following;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "followerId", referencedColumnName = "user_id")
    private User follower;

    @ColumnDefault("'N'")
    private String deleteYn;

    @Builder
    public UserFollow(Long id, User following, User follower, String deleteYn) {
        this.id = id;
        this.following = following;
        this.follower = follower;
        this.deleteYn = deleteYn;
    }
}
