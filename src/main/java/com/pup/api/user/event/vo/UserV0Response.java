package com.pup.api.user.event.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserV0Response {
    private Integer userId;
    private String email;
    private UUID userUid;
    private String profile;
    private String description;
    private Boolean isFriend;

    @Builder
    public UserV0Response(UserV0 userV0, Boolean isFriend) {
        this.userId = userV0.getUserId();
        this.email = userV0.getEmail();
        this.userUid = userV0.getUserUid();
        this.profile = userV0.getProfile();
        this.description = userV0.getDescription();
        this.isFriend = isFriend;
    }
}
