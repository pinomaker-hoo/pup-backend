package com.pup.api.walkingTrail.event.vo;

import com.pup.global.enums.OpenRangeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
public class WalkingTrailV2Response {
    private Long walkingTrailLikeId;
    private String mainImage;
    private Long walkingTrailId;
    private String name;
    private String description;
    private UUID walkingTrailUid;
    private Integer time;
    private Float distance;
    private OpenRangeEnum openRange;
    private LocalDateTime createdDate;
    private Double rating;
    private Integer userId;
    private long reviewCount;
    private long likeCount;
    private List<WalkingTrailItemV0> itemList;

    @Builder
    public WalkingTrailV2Response(WalkingTrailV2 walkingTrailV2, List<WalkingTrailItemV0> itemList) {
        this.walkingTrailLikeId = walkingTrailV2.getWalkingTrailLikeId();
        this.mainImage = walkingTrailV2.getMainImage();
        this.walkingTrailId = walkingTrailV2.getWalkingTrailId();
        this.name = walkingTrailV2.getName();
        this.description = walkingTrailV2.getDescription();
        this.walkingTrailUid = walkingTrailV2.getWalkingTrailUid();
        this.time = walkingTrailV2.getTime();
        this.distance = walkingTrailV2.getDistance();
        this.openRange = walkingTrailV2.getOpenRange();
        this.createdDate = walkingTrailV2.getCreatedDate();
        this.rating = walkingTrailV2.getRating();
        this.userId = walkingTrailV2.getUserId();
        this.reviewCount = walkingTrailV2.getReviewCount();
        this.likeCount = walkingTrailV2.getLikeCount();
        this.itemList = itemList;
    }
}
