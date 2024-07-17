package com.pup.api.walkingTrail.domain;

import com.pup.api.user.domain.User;
import com.pup.global.domain.BaseTimeEntity;
import com.pup.global.enums.OpenRangeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "TB_WALKING_TRAIL")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkingTrail extends BaseTimeEntity {
    @Id
    @Column(name = "walking_trail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailId;

    @Comment("이름")
    @Column(length = 50, nullable = false)
    private String name;

    @Comment("산책 시간")
    @Column(nullable = false)
    private Integer time;

    @Comment("산책 거리")
    @Column(nullable = false)
    private Float distance;

    @Comment("산책 기록 공개 범위")
    @Column(nullable = false, name = "open_range")
    @Enumerated(EnumType.STRING)
    private OpenRangeEnum openRange;

    @Comment("리뷰")
    @Column(length = 1000, nullable = true)
    private String review;

    @Comment("별점")
    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
