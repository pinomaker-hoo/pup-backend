package com.pup.api.user.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserV0 {
    private Integer userId;
    private String email;
    private String nickname;
    private UUID userUid;
    private String profile;
    private String description;
}
