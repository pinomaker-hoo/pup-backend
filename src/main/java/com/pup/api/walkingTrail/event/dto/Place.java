package com.pup.api.walkingTrail.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Place {
    @Schema(example = "12.12123123")
    @NotNull
    private Float lat;

    @Schema(example = "12.12123123")
    @NotNull
    private Float lng;
}
