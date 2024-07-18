package com.pup.api.walkingTrail.ui;

import com.pup.api.user.domain.User;
import com.pup.api.user.event.dto.RequestUserUpdateDto;
import com.pup.api.user.service.UserService;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailImageSaveDto;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailItemSaveDto;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailSaveDto;
import com.pup.api.walkingTrail.service.WalkingTrailDogService;
import com.pup.api.walkingTrail.service.WalkingTrailImageService;
import com.pup.api.walkingTrail.service.WalkingTrailItemService;
import com.pup.api.walkingTrail.service.WalkingTrailService;
import com.pup.global.dto.CommonResponse;
import com.pup.global.dto.SwaggerExampleValue;
import com.pup.global.dto.UserDetailDto;
import com.pup.global.jwt.JwtTokenExtractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/walking-trail")
@Tag(name = "Walking Trail API", description = "산책로 API")
@Slf4j
public class WalkingTrailController {
    private final WalkingTrailService walkingTrailService;
    private final WalkingTrailDogService walkingTrailDogService;
    private final WalkingTrailItemService walkingTrailItemService;
    private final WalkingTrailImageService walkingTrailImageService;
    private final UserService userService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "산책로 생성", description = "산책로를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로를 생성합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL))),
            @ApiResponse(responseCode = "404", description = "강아지를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_DOG_RESPONSE))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveWalkingTrail(@Valid @RequestBody RequestWalkingTrailSaveDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        WalkingTrail walkingTrail = walkingTrailService.saveWalkingTrail(user, dto);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "산책로를 생성합니다.", walkingTrail.getWalkingTrailUid());
    }

    @Operation(summary = "산책로 지점 저장", description = "산책로 지점을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로 지점을 생성합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL_ITEM))),
            @ApiResponse(responseCode = "400", description = "이미 활성화된 산책로입니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.EXIST_WALKING_TRAIL_ITEM))),
            @ApiResponse(responseCode = "404", description = "산책로를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/item")
    public ResponseEntity<?> saveWalkingTrailItem(@Valid @RequestBody RequestWalkingTrailItemSaveDto dto) {
        WalkingTrail walkingTrail = walkingTrailService.findOne(dto.getWalkingTrailUid());
        walkingTrailService.validationWalkingTrail(walkingTrail);
        walkingTrailItemService.saveWalkingTrialItem(walkingTrail, dto);
        walkingTrailService.walkingTrailToEnable(walkingTrail);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로 지점을 생성합니다.");
    }

    @Operation(summary = "산책로 이미지 저장", description = "산책로 이미지를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로 이미지를 생성합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL_IMAGE))),
            @ApiResponse(responseCode = "404", description = "산책로를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/image")
    public ResponseEntity<?> saveWalkingTrailImage(@Valid @RequestBody RequestWalkingTrailImageSaveDto dto) {
        WalkingTrail walkingTrail = walkingTrailService.findOne(dto.getWalkingTrailUid());

        walkingTrailImageService.saveWalkingTrailImage(walkingTrail, dto.getLat(), dto.getLng(), dto.getPath());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로 이미지를 생성합니다.");
    }
}