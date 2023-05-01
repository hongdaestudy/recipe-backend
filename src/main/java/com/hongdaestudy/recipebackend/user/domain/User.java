package com.hongdaestudy.recipebackend.user.domain;

import com.hongdaestudy.recipebackend.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "follower")
  private List<UserFollow> userFollows = new ArrayList<>();

  // todo: validate
  public User(String email, String password) {
    this.email = email;
    this.password = password;
    registerEvent(new UserCreatedEvent(this));
  }

  public void changeUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  @Builder
  public User(Long id, UserProfile userProfile, String email, String password) {
    this.id = id;
    this.userProfile = userProfile;
    this.email = email;
    this.password = password;
  }

  public static User create(Long id) {
    User user = new User();
    user.id = id;
    return user;
  }
}