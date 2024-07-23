package com.pup.api.walkingTrail.ui;

import com.pup.api.user.domain.User;
import com.pup.api.user.service.UserService;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.*;
import com.pup.api.walkingTrail.event.vo.*;
import com.pup.api.walkingTrail.service.*;
import com.pup.global.dto.CommonResponse;
import com.pup.global.dto.SwaggerExampleValue;
import com.pup.global.dto.UserDetailDto;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;
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

import java.util.List;
import java.util.UUID;

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
    private final WalkingTrailLikeService walkingTrailLikeService;
    private final UserService userService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "나의 산책로 리스트 조회", description = "나의 산책로 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "나의 산책로 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_WALKING_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping
    public ResponseEntity<?> findMyWalkingTrailList(HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        List<WalkingTrailV0> response = walkingTrailService.findAllByUserId(userDetailDto.getUserId());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "나의 산책로 리스트를 조회합니다.", response);
    }

    @Operation(summary = "산책로 조회", description = "산책로를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_WALKING))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "산책로를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/{walkingTrailUid}")
    public ResponseEntity<?> findWalkingTrail(@PathVariable("walkingTrailUid") String walkingTrailUid, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        WalkingTrailV1DetailResponse walkingTrail = walkingTrailService.findOne(UUID.fromString(walkingTrailUid), userDetailDto.getUserId());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "산책로를 조회합니다.", walkingTrail);
    }

    @Operation(summary = "찜한 산책로 리스트 조회", description = "찜한 산책로 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "찜한 산책로 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_LIKE_WALKING_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/like")
    public ResponseEntity<?> findLikeWalkingTrailList(HttpServletRequest httpServletRequest,
                                                      @RequestParam(value = "type", required = true) WalkingTrailSearchTypeEnum type) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        List<WalkingTrailV2> response = walkingTrailLikeService.findWalkingTrailLikeByUserId(userDetailDto.getUserId(), type);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "찜한 산책로 리스트를 조회합니다.", response);
    }

    @Operation(summary = "산책로 리스트 조회", description = "산책로 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SEARCH_WALKING_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/search")
    public ResponseEntity<?> findWalkingTrailList(HttpServletRequest httpServletRequest,
                                                  @RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "type", required = true) WalkingTrailSearchTypeEnum type) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        List<WalkingTrailV1Response> response = walkingTrailService.search(userDetailDto.getUserId(), name, type);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "산책로 리스트를 조회합니다.", response);
    }


    @Operation(summary = "산책로 생성", description = "산책로를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로를 생성합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "강아지를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_DOG_RESPONSE))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveWalkingTrail(@Valid @RequestBody RequestWalkingTrailSaveDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        WalkingTrail walkingTrail = walkingTrailService.saveWalkingTrail(user, dto);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "산책로를 생성합니다.", walkingTrail.getWalkingTrailUid());
    }

    @Operation(summary = "산책로의 강아지 수정", description = "산책로의 강아지를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로의 강아지를 수정합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.UPDATE_WALKING_TRAIL_DOG))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404.1", description = "강아지를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_DOG_RESPONSE))),
            @ApiResponse(responseCode = "404.2", description = "산책로를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PatchMapping("/dog")
    public ResponseEntity<?> updateWalkingTrailDog(@Valid @RequestBody RequestWalkingTrailDogUpdateDto dto) {
        WalkingTrail walkingTrail = walkingTrailService.findOne(dto.getWalkingTrailUid());
        walkingTrailDogService.changeWalkingTrailDog(walkingTrail, dto.getDogIdList());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로의 강아지를 변경합니다.");
    }

    @Operation(summary = "산책로 좋아요 / 좋아요 취소", description = "산책로에 좋아요를 누르거나 취소합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로의 강아지를 수정합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL_LIKE))),
            @ApiResponse(responseCode = "400", description = "이미 좋아요를 누르셨습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.EXISTED_WALKING_TRAIL_LIKE))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "좋아요를 누르지 않았습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL_LIKE))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PatchMapping("/like/{walkingTrailUid}")
    public ResponseEntity<?> updateWalkingTrailDog(@PathVariable("walkingTrailUid") String walkingTrailUid, @RequestBody RequestWalkingTrailLikeSaveDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);

        WalkingTrail walkingTrail = walkingTrailService.findOne(UUID.fromString(walkingTrailUid));
        User user = userService.findOne(userDetailDto.getUserId());

        walkingTrailLikeService.saveWalkingTrailLike(user, walkingTrail, dto.getLike());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로에 좋아요를 누르거나 취소합니다.");
    }

    @Operation(summary = "산책 종료", description = "산책을 종료합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로 지점을 생성합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL_ITEM))),
            @ApiResponse(responseCode = "400", description = "이미 활성화된 산책로입니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.EXIST_WALKING_TRAIL_ITEM))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "산책로를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PutMapping
    public ResponseEntity<?> updateWalkingTrail(@Valid @RequestBody RequestWalkingTrailUpdateDto dto, HttpServletRequest httpServletRequest) {
        WalkingTrail walkingTrail = walkingTrailService.findOne(dto.getWalkingTrailUid());
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        userService.updateLastWakingDate(user);

        walkingTrailService.validationWalkingTrail(walkingTrail);
        walkingTrailItemService.saveWalkingTrialItem(walkingTrail, dto.getPlaceList());
        walkingTrailService.walkingTrailToEnable(walkingTrail, dto);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로 지점을 생성합니다.");
    }

    @Operation(summary = "산책로 이미지 저장", description = "산책로 이미지를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로 이미지를 생성합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_WALKING_TRAIL_IMAGE))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "산책로를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_WALKING_TRAIL))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/image")
    public ResponseEntity<?> saveWalkingTrailImage(@Valid @RequestBody RequestWalkingTrailImageSaveDto dto) {
        WalkingTrail walkingTrail = walkingTrailService.findOne(dto.getWalkingTrailUid());

        walkingTrailImageService.saveWalkingTrailImage(walkingTrail, dto.getLat(), dto.getLng(), dto.getPath());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로 이미지를 생성합니다.");
    }

    @Operation(summary = "산책로 삭제", description = "산책로를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "산책로를 삭제합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.DELETE_WALKING_TRAIL_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @DeleteMapping
    public ResponseEntity<?> deleteWalkingTrailList(@RequestParam List<Long> walkingTrailIdList) {
        walkingTrailService.deleteWalkingTrail(walkingTrailIdList);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "산책로를 삭제합니다.");
    }
}