package com.example.demo.controller;

import com.example.demo.record.LoginRecord;
import com.example.demo.record.JwtAuthenticationResponseRecord;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtAuthenticationResponseRecord authenticateUser(@Valid @RequestBody LoginRecord loginRecord) {
        return authService.authenticateUser(loginRecord);
    }
}
