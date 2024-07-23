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
public class WalkingTrailV1 {
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
    private String userUid;
    private long reviewCount;
    private long likeCount;

    public WalkingTrailV1Response toResponse(Boolean isLike) {
        return WalkingTrailV1Response.builder().walkingTrailV1(this).isLike(isLike).build();
    }

    public WalkingTrailV1DetailResponse toResponse(Boolean isLike, List<String> imageList, List<WalkingTrailItemV0> itemList) {
        return WalkingTrailV1DetailResponse.builder().walkingTrailV1(this).isLike(isLike).imageList(imageList).itemList(itemList).build();
    }
}
