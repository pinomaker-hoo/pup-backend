package com.pup.api.walkingTrail.event.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ToString
@Builder
@Getter
public class WalkingTrailReviewResponse {
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
    private List<WalkingTrailItemV0> itemList;
}
