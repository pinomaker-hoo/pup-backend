package com.pup.api.user.ui;

import com.pup.api.user.domain.User;
import com.pup.api.user.event.dto.RequestUserPasswordDto;
import com.pup.api.user.event.dto.RequestUserUpdateDto;
import com.pup.api.user.event.vo.UserV0;
import com.pup.api.user.event.vo.UserV0Response;
import com.pup.api.user.event.vo.UserV1;
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
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "유저 관련 API")
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "유저 정보 조회", description = "유저 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_USER))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping
    public ResponseEntity<?> findUser(HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        UserV0 user = userService.findUserV0One(userDetailDto.getUserId());


        return CommonResponse.createResponse(HttpStatus.OK.value(), "유저 정보를 조회합니다.", user);
    }

    @Operation(summary = "유저 정보 조회", description = "유저 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_USER_RESPONSE))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "유저 정보를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.NOT_FOUND_USER)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/{userId}")
    public ResponseEntity<?> findUser(HttpServletRequest httpServletRequest, @PathVariable("userId") Integer userId) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        UserV0Response user = userService.findUserV0Response(userDetailDto.getUserId(), userId);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "유저 정보를 조회합니다.", user);
    }

    @Operation(summary = "친구 대상 조회", description = "친구 대상 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 대상 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_USER_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/search")
    public ResponseEntity<?> findUserList(HttpServletRequest httpServletRequest, @RequestParam(value = "uid", required = false) String uid) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        List<UserV1> userList = userService.findUserList(userDetailDto.getUserId(), uid);


        return CommonResponse.createResponse(HttpStatus.OK.value(), "친구 대상 리스트를 조회합니다.", userList);
    }


    @Operation(summary = "유저 정보 수정", description = "유저 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보를 수정합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.UPDATE_USER))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody RequestUserUpdateDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        userService.updateUser(user, dto);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "유저 정보를 수정합니다.");
    }

    @Operation(summary = "유저 비밀번호 수정", description = "유저 비밀번호를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 비밀번호를 수정합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.UPDATE_USER_PASSWORD))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody RequestUserPasswordDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        userService.validationPassword(user, dto.getCurrentPassword());
        userService.updatePassword(user, dto.getPassword());

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "유저 비밀번호를 수정합니다.");
    }
}
