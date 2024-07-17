package com.pup.api.walkingTrail.domain;

import com.pup.global.domain.BaseTimeCreatedEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "TB_WALKING_TRAIL_IMAGE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkingTrailImage extends BaseTimeCreatedEntity {
    @Id
    @Column(name = "walking_trail_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailImageId;

    @Comment("이미지 경로")
    @Column(length = 255, nullable = false)
    private String path;

    @Comment("위도")
    @Column(nullable = false)
    private Float lat;

    @Comment("경도")
    @Column(nullable = false)
    private Float lng;

    @ManyToOne
    @JoinColumn(name = "walking_trail_id", nullable = false)
    private WalkingTrail walkingTrail;
}
