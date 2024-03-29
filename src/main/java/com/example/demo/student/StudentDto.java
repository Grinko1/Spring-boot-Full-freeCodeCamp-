package com.example.demo.student;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record StudentDto(
        @NotEmpty(message = "firstname should not be empty ")
        String firstname,
        @NotEmpty(message = "Lastname should not be empty ")
        String lastname,
        String email,
        int age,
        Integer schoolId
) {
}
