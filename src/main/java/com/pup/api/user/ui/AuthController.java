package com.pup.api.user.ui;

import com.pup.api.user.event.dto.*;
import com.pup.api.user.event.vo.LoginResponse;
import com.pup.api.user.event.vo.LoginUser;
import com.pup.api.user.service.AuthService;
import com.pup.api.user.service.UserService;
import com.pup.global.dto.CommonResponse;
import com.pup.global.dto.TokenDto;
import com.pup.global.dto.TokenSingleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pup.global.dto.SwaggerExampleValue;

import static com.pup.api.user.domain.UserSocialTypeEnum.PUP;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth API", description = "인증 관련 API")
@Slf4j
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "PUP 유저 생성", description = "PUP 유저를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "이미 사용 중인 이메일 입니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.USER_SAVE_EXISTED_NUMBER_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody RequestUserSaveDto dto) {
        userService.existedUserByEmail(dto.getEmail());
        userService.saveUser(dto);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "회원가입에 성공하였습니다.");
    }

    @Operation(summary = "PUP 유저 로그인", description = "로그인, 아이디와 비밀번호를 받아 유효성 및 유저 조회, 비밀번호 검증 후 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인에 성공했습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_LOGIN_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "비밀번호가 같지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.USER_LOGIN_NOT_MATCH_PASSWORD_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.USER_LOGIN_NOT_FOUND_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody RequestUserLoginDto dto) {
        LoginUser user = userService.findOne(dto.getEmail());
        userService.validationPassword(user, dto.getPassword());

        TokenDto token = authService.generateToken(user.getUserId());
        LoginResponse response = user.toResponse(token);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "로그인에 성공하였습니다.", response);
    }

    @Operation(summary = "PUP 소셜 유저 생성", description = "PUP 유저를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_SAVE_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "이미 사용 중인 이메일 입니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.USER_SAVE_EXISTED_NUMBER_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/social")
    public ResponseEntity<?> saveSocialUser(@Valid @RequestBody RequestSocialUserSaveDto dto) {
        userService.existedUserByEmail(dto.getEmail());
        userService.saveSocialUser(dto);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "회원가입에 성공하였습니다.");
    }

    @Operation(summary = "PUP 소셜 유저 로그인", description = "로그인, 아이디와 비밀번호를 받아 유효성 및 유저 조회, 비밀번호 검증 후 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인에 성공했습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.USER_LOGIN_RESPONSE))),
            @ApiResponse(responseCode = "400", description = "비밀번호가 같지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.USER_LOGIN_NOT_MATCH_PASSWORD_RESPONSE)})),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.USER_LOGIN_NOT_FOUND_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/social/login")
    public ResponseEntity<?> loginSocialUser(@Valid @RequestBody RequestSocialUserLoginDto dto) {
        LoginUser user = userService.findOne(dto.getToken(), dto.getSocialTypeEnum());
        TokenDto token = authService.generateToken(user.getUserId());
        LoginResponse response = user.toResponse(token);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "로그인에 성공하였습니다.", response);
    }

    @Operation(summary = "Reissue Token", description = "토큰 재발급, refresh token 유효성 검사 후 토큰을 재발급 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰을 재발급 받습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.REISSUE_TOKEN_RESPONSE))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping("/reissue")
    public ResponseEntity<?> reissueToken(@Valid @RequestBody RequestTokenReissueDto dto) {
        TokenSingleDto token = authService.reissueToken(dto);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "토큰 재발급에 성공합니다.", token);
    }
}
