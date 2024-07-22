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
public class WalkingTrailV1DetailResponse {
    private Long walkingTrailId;
    private String mainImage;
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
    private Boolean isLike;
    private List<String> imageList;

    @Builder
    public WalkingTrailV1DetailResponse(WalkingTrailV1 walkingTrailV1, Boolean isLike, List<String> imageList) {
        this.walkingTrailId = walkingTrailV1.getWalkingTrailId();
        this.mainImage = walkingTrailV1.getMainImage();
        this.name = walkingTrailV1.getName();
        this.description = walkingTrailV1.getDescription();
        this.walkingTrailUid = walkingTrailV1.getWalkingTrailUid();
        this.time = walkingTrailV1.getTime();
        this.distance = walkingTrailV1.getDistance();
        this.openRange = walkingTrailV1.getOpenRange();
        this.createdDate = walkingTrailV1.getCreatedDate();
        this.rating = walkingTrailV1.getRating();
        this.userId = walkingTrailV1.getUserId();
        this.reviewCount = walkingTrailV1.getReviewCount();
        this.likeCount = walkingTrailV1.getLikeCount();
        this.isLike = isLike;
        this.imageList = imageList;
    }
}
