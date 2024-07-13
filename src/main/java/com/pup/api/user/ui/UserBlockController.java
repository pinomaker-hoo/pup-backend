package com.pup.api.user.ui;

import com.pup.api.user.service.UserBlockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user-block")
@Tag(name = "User Block API", description = "유저 차단 관련 API")
@Slf4j
public class UserBlockController {
    private final UserBlockService userBlockService;
}
