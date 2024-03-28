package com.example.demo.dto;

import com.example.demo.model.School;
import com.example.demo.model.StudentProfile;

public record StudentResponseDto (
        String firstname,
        String lastname,
        String email,
        int age,
        Integer schoolId,
        StudentProfile studentProfile
){
}
