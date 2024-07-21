package com.pup.api.walkingTrail.domain;

import com.pup.api.user.domain.User;
import com.pup.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "TB_WALKING_TRAIL_LIKE")
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkingTrailLike extends BaseTimeEntity {
    @Id
    @Column(name = "walking_trail_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailLikeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "walking_trail_id", nullable = false)
    private WalkingTrail walkingTrail;
}
