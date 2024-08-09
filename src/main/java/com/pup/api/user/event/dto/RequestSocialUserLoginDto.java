package com.pup.api.user.event.dto;

import com.pup.api.user.domain.UserSocialTypeEnum;
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
public class RequestSocialUserLoginDto {
    @Schema(example = "123123123123")
    @NotNull
    private String token;

    @Schema(example = "GOOGLE")
    @NotNull
    private UserSocialTypeEnum socialTypeEnum;
}
