package com.example.demo.record;

public record JwtAuthenticationResponseRecord(String accessToken, String tokenType) {
    public JwtAuthenticationResponseRecord(String accessToken) {
        this(accessToken, "Bearer");
    }
}
