package com.pup.api.friend.event.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class FriendV0 {
    private Long friendId;
    private Integer userId;
    private String userUid;
    private String description;
    private String profile;
    private LocalDateTime lastWakingDate;
    private List<String> dogProfileList;
}
