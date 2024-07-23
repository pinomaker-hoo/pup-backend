package com.pup.api.user.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserV1 {
    private Integer userId;
    private String userUid;
    private String profile;
    private List<String> dogProfileList;
}
