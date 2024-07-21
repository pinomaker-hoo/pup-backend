package com.pup.api.user.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserV1 {
    private Integer userId;
    private UUID userUid;
    private String nickname;
    private String profile;
    private List<String> dogProfileList;
}
