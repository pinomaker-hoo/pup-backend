package com.pup.api.user.event.dto;

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
public class RequestUserPasswordDto {
    @Schema(example = "1234")
    @NotNull
    private String currentPassword;

    @Schema(example = "1234")
    @NotNull
    private String password;
}
