package com.pup.api.user.service;

import com.pup.api.user.event.dto.RequestUserLoginDto;
import org.springframework.http.ResponseEntity;
import com.pup.api.user.event.dto.RequestTokenReissueDto;
import com.pup.api.user.event.dto.RequestUserSaveDto;

public interface UserService {
    ResponseEntity<?> saveUser(RequestUserSaveDto dto);

    ResponseEntity<?> loginUser(RequestUserLoginDto dto);

    ResponseEntity<?> reissueToken(RequestTokenReissueDto dto);
}
