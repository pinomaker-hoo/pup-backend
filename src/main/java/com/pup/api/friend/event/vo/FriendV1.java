package com.pup.api.friend.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FriendV1 {
    private Long friendId;
    private Integer userId;
    private Integer friendUserId;
}
