package com.example.demo.dto;

public record StudentDto(
         String firstname,
         String lastname,
         String email,
         int age,
         Integer schoolId
) {
}
