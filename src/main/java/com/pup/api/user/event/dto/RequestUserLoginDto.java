package com.pup.api.user.event.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUserLoginDto {
    @Schema(example = "test@test.com")
    @Size(max = 30, message = "30자 이내로 입력하세요.")
    @NotNull
    private String email;

    @Schema(example = "1234")
    @NotNull
    private String password;
}
