package com.pup.api.user.ui;

import com.pup.api.user.service.UserBlockService;
import com.pup.global.dto.CommonResponse;
import com.pup.global.dto.SwaggerExampleValue;
import com.pup.global.dto.UserDetailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pup.global.utils.PermissionUtils.getUserDetailDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user-block")
@Tag(name = "User Block API", description = "유저 차단 관련 API")
@Slf4j
public class UserBlockController {
    private final UserBlockService userBlockService;
}
