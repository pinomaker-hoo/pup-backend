package com.pup.api.user.event.vo;

import com.pup.global.dto.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private Integer userId;
    private String email;
    private String userUid;
    private TokenDto token;
}
