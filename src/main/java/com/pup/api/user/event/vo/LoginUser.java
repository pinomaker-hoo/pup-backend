package com.pup.api.user.event.vo;

import com.pup.global.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class LoginUser {
    private Integer userId;
    private String email;
    private String password;
    private UUID userUid;

    public LoginResponse toResponse(TokenDto token) {
        return new LoginResponse(userId, email, userUid, token);
    }
}
