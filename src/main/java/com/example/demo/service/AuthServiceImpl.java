package com.example.demo.service;

import com.example.demo.record.LoginRecord;
import com.example.demo.record.JwtAuthenticationResponseRecord;
import com.example.demo.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public JwtAuthenticationResponseRecord authenticateUser(LoginRecord loginRecord) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRecord.name(),
                        loginRecord.surname()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        log.info("User " + loginRecord.surname() + " has logged in.");
        return new JwtAuthenticationResponseRecord(jwt);
    }
}
