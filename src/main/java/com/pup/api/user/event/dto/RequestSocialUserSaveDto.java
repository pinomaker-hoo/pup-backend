package com.pup.api.user.event.dto;

import com.pup.api.user.domain.User;
import com.pup.api.user.domain.UserSocialTypeEnum;
import com.pup.global.enums.OpenRangeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestSocialUserSaveDto {
    @Schema(example = "test@test.com")
    @NotNull
    private String email;

    @Schema(example = "GOOGLE")
    @NotNull
    private UserSocialTypeEnum userSocialTypeEnum;

    @Schema(example = "asdasdaa")
    @NotNull
    private String socialToken;

    public User toUser() {
        return User.builder()
                .email(this.email)
                .socialToken(socialToken)
                .socialType(UserSocialTypeEnum.GOOGLE)
                .profile("")
                .description("")
                .openRange(OpenRangeEnum.PUBLIC)
                .userUid(generateUid())
                .build();
    }

    private String generateUid() {
        String uuidString = UUID.randomUUID().toString();

        byte[] uuidStringBytes = uuidString.getBytes(StandardCharsets.UTF_8);
        byte[] hashBytes;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            hashBytes = messageDigest.digest(uuidStringBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 4; j++) {
            sb.append(String.format("%02x", hashBytes[j]));
        }

        return sb.toString();
    }
}