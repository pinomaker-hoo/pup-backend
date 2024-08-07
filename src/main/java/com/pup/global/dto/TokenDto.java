package com.pup.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String accessToken;
    private String refreshToken;

    public TokenSingleDto toSingleTokenDto() {
        return TokenSingleDto.builder()
                .accessToken(this.accessToken)
                .build();
    }
}
