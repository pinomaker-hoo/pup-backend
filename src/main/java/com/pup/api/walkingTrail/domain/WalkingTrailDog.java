package com.pup.api.walkingTrail.domain;

import com.pup.api.dog.domain.Dog;
import com.pup.global.domain.BaseTimeCreatedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_WALKING_TRAIL_DOG")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkingTrailDog extends BaseTimeCreatedEntity {
    @Id
    @Column(name = "walking_trail_dog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailDogId;

    @ManyToOne
    @JoinColumn(name = "walking_trail_id", nullable = false)
    private WalkingTrail walkingTrail;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;
}
