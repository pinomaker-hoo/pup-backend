package com.pup.api.walkingTrail.event.vo;

import com.pup.global.enums.OpenRangeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class WalkingTrailV0Response {
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
    private List<WalkingTrailItemV0> itemList;

    @Builder
    public WalkingTrailV0Response(WalkingTrailV0 walkingTrailV0, List<WalkingTrailItemV0> itemList) {
        this.walkingTrailId = walkingTrailV0.getWalkingTrailId();
        this.mainImage = walkingTrailV0.getMainImage();
        this.name = walkingTrailV0.getName();
        this.description = walkingTrailV0.getDescription();
        this.walkingTrailUid = walkingTrailV0.getWalkingTrailUid();
        this.time = walkingTrailV0.getTime();
        this.distance = walkingTrailV0.getDistance();
        this.openRange = walkingTrailV0.getOpenRange();
        this.createdDate = walkingTrailV0.getCreatedDate();
        this.rating = walkingTrailV0.getRating();
        this.itemList = itemList;
    }
}
