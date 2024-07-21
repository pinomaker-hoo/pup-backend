package com.pup.api.friend.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class FriendV1 {
    private Long friendId;
    private Integer userId;
    private Integer friendUserId;
}
