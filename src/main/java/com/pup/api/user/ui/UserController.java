package com.pup.api.user.ui;

import com.pup.api.dog.event.dto.RequestDogSaveDto;
import com.pup.api.user.domain.User;
import com.pup.api.user.event.dto.RequestUserUpdateDto;
import com.pup.api.user.event.vo.UserV0;
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
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/")
    public ResponseEntity<?> findUser(HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        UserV0 user = userService.findUserV0One(userDetailDto.getUserId());


        return CommonResponse.createResponse(HttpStatus.OK.value(), "유저 정보를 조회합니다.", user);
    }

    @Operation(summary = "유저 정보 수정", description = "유저 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보를 수정합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.UPDATE_USER))),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PutMapping("/")
    public ResponseEntity<?> updateUser(@Valid @RequestBody RequestUserUpdateDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        userService.updateUser(user, dto);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "유저 정보를 수정합니다.");
    }
}
