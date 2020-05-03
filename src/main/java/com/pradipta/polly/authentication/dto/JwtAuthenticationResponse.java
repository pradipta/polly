package com.pradipta.polly.authentication.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token) {
        this.accessToken = token;
    }
}
