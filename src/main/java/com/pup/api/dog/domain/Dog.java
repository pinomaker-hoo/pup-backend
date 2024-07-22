package com.pup.api.dog.domain;

import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrailDog;
import com.pup.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_DOG")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dog extends BaseTimeEntity {
    @Id
    @Column(name = "dog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogId;

    @Comment("이름")
    @Column(length = 50, nullable = false)
    private String name;

    @Comment("프로필 이미지")
    @Column(length = 255, nullable = false)
    private String profile;

    @Comment("생일")
    @Column(nullable = false)
    private LocalDate birth;

    @Comment("중성화 여부")
    @Column(nullable = false, name = "is_neutered")
    private Boolean isNeutered;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "dog", cascade = CascadeType.REMOVE)
    private List<WalkingTrailDog> walkingTrailDogList;

    public void changeDog(String name, String profile, LocalDate birth, Boolean isNeutered) {
        this.name = name;
        this.profile = profile;
        this.birth = birth;
        this.isNeutered = isNeutered;
    }
}
