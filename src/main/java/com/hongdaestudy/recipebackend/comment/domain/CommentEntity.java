package com.hongdaestudy.recipebackend.comment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "comment")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private Long recipeId;

    // TODO sieun: userProfileId -> userId (프로필 변경시, 변경전 프로필이 보일수 있음)
    private Long userId;

    private String comment;

    private String parentCommentId;

    private String level;

    private String sortOrder;

    private String score;

    private String photoUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}