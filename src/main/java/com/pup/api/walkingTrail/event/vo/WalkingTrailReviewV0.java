package com.pup.api.walkingTrail.event.vo;

import com.pup.global.enums.OpenRangeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
public class WalkingTrailReviewV0 {
    private Long walkingTrailReviewId;
    private String mainImage;
    private Long walkingTrailId;
    private String name;
    private String description;
    private UUID walkingTrailUid;
    private Integer time;
    private Float distance;
    private LocalDateTime createdDate;
    private Double rating;
    private Integer userId;
    private String userUid;
    private long reviewCount;

    public WalkingTrailReviewResponse toResponse(List<WalkingTrailItemV0> itemV0List) {
        return WalkingTrailReviewResponse.builder()
                .walkingTrailReviewId(walkingTrailReviewId)
                .mainImage(mainImage)
                .walkingTrailId(walkingTrailId)
                .name(name)
                .description(description)
                .walkingTrailUid(walkingTrailUid)
                .time(time)
                .distance(distance)
                .createdDate(createdDate)
                .rating(rating)
                .userId(userId)
                .userUid(userUid)
                .reviewCount(reviewCount)
                .itemList(itemV0List)
                .build();
    }
}
