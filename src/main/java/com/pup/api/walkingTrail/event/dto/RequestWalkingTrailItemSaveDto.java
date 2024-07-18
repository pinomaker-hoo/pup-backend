package com.pup.api.walkingTrail.event.dto;

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
public class RequestWalkingTrailItemSaveDto {
    @Schema(example = "11231231-23123123")
    @NotNull
    private UUID walkingTrailUid;

    private List<Place> placeList;
}
