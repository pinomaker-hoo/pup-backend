package com.pup.global.dto;

import com.pup.global.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailDto {
    private Integer userId;
    private UserRole role;
}
