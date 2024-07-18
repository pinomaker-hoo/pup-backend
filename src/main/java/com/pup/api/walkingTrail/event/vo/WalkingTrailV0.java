package com.pup.api.walkingTrail.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class WalkingTrailV0 {
    private Long walkingTrailId;
    private String name;
    private String description;
    private UUID walkingTrailUid;
    private Integer time;
    private Float distance;
    private String openRange;
    private LocalDateTime createdDate;
}
