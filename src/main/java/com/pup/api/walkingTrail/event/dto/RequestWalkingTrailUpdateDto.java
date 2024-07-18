package com.pup.api.walkingTrail.event.dto;

import com.pup.global.enums.OpenRangeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestWalkingTrailUpdateDto {
    @Schema(example = "11231231-23123123")
    @NotNull
    private UUID walkingTrailUid;

    @Schema(example = "서울시 어쩌구")
    @NotNull
    private String name;

    @Schema(example = "120", description = "산책 시간, 단위는 초")
    @NotNull
    private Integer time;

    @Schema(example = "120f", description = "산책 거리, 단위는 자유")
    @NotNull
    private Float distance;

    @Schema(example = "해당 산책에 대한 기록을 저장합니다.")
    @NotNull
    private String description;

    @Schema(example = "PROTECTED", description = "PUBLIC, PRIVATE, FRIEND")
    @NotNull
    private OpenRangeEnum openRange;

    private List<Place> placeList;
}
