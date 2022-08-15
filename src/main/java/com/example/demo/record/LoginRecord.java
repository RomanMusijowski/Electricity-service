package com.example.demo.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public record LoginRecord(@NotBlank String name,
                          @NotBlank String surname) {
}
