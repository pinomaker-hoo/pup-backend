package com.pup.api.walkingTrail.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestWalkingTrailReviewSaveDto {
    @Schema(example = "11231231-23123123")
    @NotNull
    private UUID walkingTrailUid;

    @Schema(example = "120", description = "산책 시간, 단위는 초")
    @NotNull
    private Integer time;

    @Schema(example = "3.5", description = "별점")
    @NotNull
    private Float rating;
}
