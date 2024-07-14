package com.pup.api.user.event.dto;

import com.pup.api.user.domain.User;
import com.pup.api.user.domain.UserSocialTypeEnum;
import com.pup.global.enums.OpenRangeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUserUpdateDto {
    @Schema(example = "https://123.png")
    @NotNull
    private String profile;

    @Schema(example = "안녕하세요. 저는 000 입니다.")
    @Size(max = 30, message = "30자 이내로 입력하세요.")
    @NotNull
    private String description;
}
