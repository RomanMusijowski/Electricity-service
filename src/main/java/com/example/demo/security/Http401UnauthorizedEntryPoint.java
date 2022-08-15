package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        log.info("Pre-authenticated entry point called. Rejecting access: " + makeUrl(request) + ". " + arg2.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }

    private String makeUrl(HttpServletRequest request) {
        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }
}
