package com.example.demo.service;

import com.example.demo.record.LoginRecord;
import com.example.demo.record.JwtAuthenticationResponseRecord;

public interface AuthService {
    JwtAuthenticationResponseRecord authenticateUser(LoginRecord loginRequest);
}
