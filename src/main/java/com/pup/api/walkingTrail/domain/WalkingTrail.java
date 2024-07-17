package com.pup.api.walkingTrail.domain;

import com.pup.api.dog.domain.Dog;
import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailSaveDto;
import com.pup.global.domain.BaseTimeEntity;
import com.pup.global.enums.OpenRangeEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_WALKING_TRAIL")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WalkingTrail extends BaseTimeEntity {
    @Id
    @Column(name = "walking_trail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingTrailId;

    @Comment("이름")
    @Column(length = 50, nullable = true)
    private String name;

    @Comment("산책로 UID")
    @Column(nullable = false, name = "walking_trail_uid")
    private UUID walkingTrailUid;

    @Comment("산책 시간")
    @Column(nullable = false)
    private Integer time;

    @Comment("산책 거리")
    @Column(nullable = false)
    private Float distance;

    @Comment("산책 기록 공개 범위")
    @Column(nullable = true, name = "open_range")
    @Enumerated(EnumType.STRING)
    private OpenRangeEnum openRange;

    @Comment("리뷰")
    @Column(length = 1000, nullable = true)
    private String review;

    @Comment("별점")
    @Column(nullable = true)
    private Integer rating;

    @OneToMany(mappedBy = "walkingTrail", cascade = CascadeType.REMOVE)
    private List<WalkingTrailItem> walkingTrailItemList;

    @OneToMany(mappedBy = "walkingTrail", cascade = CascadeType.REMOVE)
    private List<WalkingTrailImage> walkingTrailImageList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public WalkingTrail(User user) {
        this.time = 0;
        this.distance = 0f;
        this.user = user;
        this.walkingTrailUid = UUID.randomUUID();
    }

    public WalkingTrailItem toWalkingTrailItem(Float lat, Float lng) {
        return WalkingTrailItem.builder()
                .walkingTrail(this)
                .lat(lat)
                .lng(lng)
                .build();
    }

    public WalkingTrailDog toWalkingTrailDog(Dog dog) {
        return WalkingTrailDog.builder()
                .walkingTrail(this)
                .dog(dog)
                .build();
    }

    public WalkingTrailImage toWalkingTrailImage(Float lat, Float lng, String path) {
        return WalkingTrailImage.builder()
                .walkingTrail(this)
                .lat(lat)
                .lng(lng)
                .path(path)
                .build();
    }
}
