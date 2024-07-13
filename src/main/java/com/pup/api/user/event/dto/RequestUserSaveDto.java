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
public class RequestUserSaveDto {
    @Schema(example = "test@test.com")
    @Size(max = 50, message = "30자 이내로 입력하세요.")
    @NotNull
    private String email;

    @Schema(example = "1234")
    @NotNull
    private String password;

    public User toUser(String password) {
        return User.builder()
                .email(this.email)
                .password(password)
                .nickname("")
                .socialType(UserSocialTypeEnum.PUP)
                .profile("")
                .description("")
                .openRange(OpenRangeEnum.PUBLIC)
                .userUid(UUID.randomUUID())
                .build();
    }
}
