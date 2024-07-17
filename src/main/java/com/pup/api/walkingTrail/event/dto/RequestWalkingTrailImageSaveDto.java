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
public class RequestWalkingTrailImageSaveDto {
    @Schema(example = "11231231-23123123")
    @NotNull
    private UUID walkingTrailUid;

    @Schema(example = "https://123.png")
    @NotNull
    private String path;

    @Schema(example = "12.12123123")
    @NotNull
    private Float lat;

    @Schema(example = "12.12123123")
    @NotNull
    private Float lng;
}
