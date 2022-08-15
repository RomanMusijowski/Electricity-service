package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthenticationService {

    public Authentication getAccountFromContext(){
        return  SecurityContextHolder.getContext().getAuthentication();

    }
}
