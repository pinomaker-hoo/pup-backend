package com.pup.api.dog.ui;

import com.pup.api.dog.service.DogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/dog")
@Tag(name = "Dog API", description = "반려견 관련 API")
@Slf4j
public class DogController {
    private final DogService dogService;

}
