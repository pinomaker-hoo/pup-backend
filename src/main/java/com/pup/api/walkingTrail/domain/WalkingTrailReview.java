package com.pup.api.walkingTrail.domain;

import com.pup.api.user.domain.User;
import com.pup.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;


@Entity
@Table(name = "TB_WALKING_TRAIL_REVIEW")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WalkingTrailReview extends BaseTimeEntity {
    @Id
    @Column(name = "walking_trail_review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailReviewId;

    @Comment("별점")
    @Column(nullable = false)
    private Float rating;

    @Comment("산책 시간")
    @Column(nullable = false)
    private Integer time;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "walking_trail_id", nullable = false)
    private WalkingTrail walkingTrail;

    @Builder
    public WalkingTrailReview(Float rating, Integer time, User user, WalkingTrail walkingTrail) {
        this.rating = rating;
        this.time = time;
        this.user = user;
        this.walkingTrail = walkingTrail;
    }
}
