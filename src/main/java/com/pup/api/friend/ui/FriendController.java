package com.pup.api.friend.ui;

import com.pup.api.dog.event.dto.RequestDogSaveDto;
import com.pup.api.friend.event.dto.RequestFriendSaveDto;
import com.pup.api.friend.event.vo.FriendV0;
import com.pup.api.friend.service.FriendService;
import com.pup.api.user.domain.User;
import com.pup.api.user.service.UserService;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/friend")
@Tag(name = "Friend API", description = "친구 관련 API")
@Slf4j
public class FriendController {
    private final FriendService friendService;
    private final UserService userService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "친구 생성", description = "친구를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "강아지 저장에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_FRIEND_RESPONSE))),
            @ApiResponse(responseCode = "400-1", description = "이미 친구로 등록된 사용자 입니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.EXISTED_FRIEND_RESPONSE))),
            @ApiResponse(responseCode = "400-2", description = "", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_SELF_SAVE_FRIEND_RESPONSE))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "강아지 저장에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.NOT_FOUND_USER_RESPONSE))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveFriend(@Valid @RequestBody RequestFriendSaveDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        friendService.validationSaveFriend(userDetailDto.getUserId(), dto.getUserId());

        User user = userService.findOne(userDetailDto.getUserId());
        User targetUser = userService.findOne(dto.getUserId());
        friendService.saveFriend(user, targetUser);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "친구를 등록합니다.");
    }

    @Operation(summary = "친구 리스트 조회", description = "친구 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_FRIEND_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping
    public ResponseEntity<?> findFriendList(HttpServletRequest httpServletRequest, @RequestParam(value = "name", required = false) String name) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);

        List<FriendV0> response = friendService.findFriendList(userDetailDto.getUserId(), name);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "친구 리스트를 조회합니다.", response);
    }

    @Operation(summary = "친구 삭제", description = "친구를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구를 삭제합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.DELETE_FRIEND_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @DeleteMapping
    public ResponseEntity<?> deleteFriendList(@RequestParam List<Long> friendIds) {
        friendService.deleteFriend(friendIds);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "친구를 삭제합니다.");
    }
}
