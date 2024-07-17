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
@Table(name = "TB_WALKING_TRAIL_ITEM")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WalkingTrailItem extends BaseTimeEntity {
    @Id
    @Column(name = "walking_trail_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailItemId;

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
