package com.hongdaestudy.recipebackend.recipe.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO jaesay: 재료그룹, 태그 맵핑 추가
@Table(name = "recipe")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    // TODO jaesay: ID 값을 그냥 Long으로 할지 클래스로 하나 정의할지..
    private Long id;

    private Long memberId;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RecipeStepEntity> recipeSteps = new ArrayList<>();

    // TODO jaesay: 컬럼들 상세 정보들 추가할지.. null 여부, 코멘트 등등...
    private String title;

    private String description;

    private String videoUrl;

    @Embedded
    private RecipeInformation information;

    private String completionPhotoUrl;

    private String tip;

    // TODO jaesay: Enum 값에 attribute converter 추가할지... , description 값은 프론트에 의존적이라 enum 그대로 저장하면 될것같다
    @Enumerated(EnumType.STRING)
    private RecipeStatus status;

    // TODO jaesay: Auditing 으로 뺼지...
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RecipeEntity that = (RecipeEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addRecipeStep(RecipeStepEntity recipeStep) {
        this.recipeSteps.add(recipeStep);
        recipeStep.setRecipe(this);
    }
}
