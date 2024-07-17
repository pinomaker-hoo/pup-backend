package com.pup.api.walkingTrail.ui;

import com.pup.api.walkingTrail.service.WalkingTrailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/walking-trail")
@Tag(name = "Walking Trail API", description = "산책로 API")
@Slf4j
public class WalkingTrailController {
    private final WalkingTrailService walkingTrailService;

}
