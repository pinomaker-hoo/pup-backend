package com.pup.api.friend.ui;

import com.pup.api.friend.service.FriendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/friend")
@Tag(name = "Friend API", description = "친구 관련 API")
@Slf4j
public class FriendController {
    private final FriendService friendService;

}
