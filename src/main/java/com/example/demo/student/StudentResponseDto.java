package com.example.demo.student;

import com.example.demo.studentProfile.StudentProfile;

public record StudentResponseDto (
        String firstname,
        String lastname,
        String email,
        int age,
        Integer schoolId,
        StudentProfile studentProfile
){
}
