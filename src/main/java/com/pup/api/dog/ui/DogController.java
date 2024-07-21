package com.pup.api.dog.ui;

import com.pup.api.dog.event.dto.RequestDogSaveDto;
import com.pup.api.dog.event.vo.DogV0;
import com.pup.api.dog.service.DogService;
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
@RequestMapping("/api/v1/dog")
@Tag(name = "Dog API", description = "반려견 관련 API")
@Slf4j
public class DogController {
    private final DogService dogService;
    private final UserService userService;
    private final JwtTokenExtractor jwtTokenExtractor;

    @Operation(summary = "강아지 생성", description = "강아지를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "강아지 저장에 성공하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.SAVE_DOG))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @PostMapping
    public ResponseEntity<?> saveDog(@Valid @RequestBody RequestDogSaveDto dto, HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        User user = userService.findOne(userDetailDto.getUserId());
        dogService.saveDogList(user, dto);

        return CommonResponse.createResponseMessage(HttpStatus.OK.value(), "강아지 저장에 성공 했습니다.");
    }

    @Operation(summary = "나의 강아지 리스트 조회", description = "나의 강아지 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "나의 강아지 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_DOG_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping
    public ResponseEntity<?> findMyDogList(HttpServletRequest httpServletRequest) {
        UserDetailDto userDetailDto = jwtTokenExtractor.extractUserId(httpServletRequest);
        List<DogV0> response = dogService.findDogListByUserId(userDetailDto.getUserId());

        return CommonResponse.createResponse(HttpStatus.OK.value(), "강아지 리스트를 조회합니다.", response);
    }

    @Operation(summary = "강아지 리스트 조회", description = "강아지 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "강아지 리스트를 조회합니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.FIND_DOG_LIST))),
            @ApiResponse(responseCode = "401", description = "토큰 정보가 유효하지 않습니다.", content = @Content(mediaType = "application/json", examples = {@ExampleObject(value = SwaggerExampleValue.UN_AUTHENTICATION_RESPONSE)})),
            @ApiResponse(responseCode = "500", description = "서버에서 에러가 발생하였습니다.", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = SwaggerExampleValue.INTERNAL_SERVER_ERROR_RESPONSE)))})
    @GetMapping("/{userId}")
    public ResponseEntity<?> findDogList(@PathVariable("userId") Integer userId) {
        List<DogV0> response = dogService.findDogListByUserId(userId);

        return CommonResponse.createResponse(HttpStatus.OK.value(), "강아지 리스트를 조회합니다.", response);
    }
}
