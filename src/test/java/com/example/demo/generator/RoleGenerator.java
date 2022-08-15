package com.example.demo.generator;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;

public class RoleGenerator {

    public static Role generateUserRole() {
        return Role.builder()
                .name(RoleName.ROLE_USER)
                .build();
    }
}
