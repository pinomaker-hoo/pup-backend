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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                .socialType(UserSocialTypeEnum.PUP)
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