package com.pup.api.dog.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveDog {
    @Schema(example = "뽀삐")
    @NotNull
    private String name;

    @Schema(example = "https://")
    @NotNull
    private String profile;

    @Schema(example = "2021-01-01")
    @NotNull
    private LocalDate birth;

    @Schema(example = "false")
    @NotNull
    private Boolean isNeutered;
}
