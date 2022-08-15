package com.example.demo.generator;

import com.example.demo.entity.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class AccountGenerator {

    public static Account generateAccount() {
        return Account.builder()
                .name("account1")
                .surname("surname1")
                .roles(Set.of(RoleGenerator.generateUserRole()))
                .services(Collections.emptyList())
                .build();
    }
}
