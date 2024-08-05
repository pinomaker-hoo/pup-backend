package com.pup.api.walkingTrail.event.vo;

import com.pup.api.walkingTrail.domain.WalkingTrailItem;
import com.pup.global.enums.OpenRangeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class WalkingTrailV0 {
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

    public WalkingTrailV0Response toResponse(List<WalkingTrailItemV0> itemList) {
        return WalkingTrailV0Response.builder().walkingTrailV0(this).itemList(itemList).build();
    }
}
