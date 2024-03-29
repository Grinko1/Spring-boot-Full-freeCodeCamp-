package com.example.demo.school;

import com.example.demo.student.Student;

import java.util.List;

public record SchoolDto(
         String name,
         List<Student> students
) {
}
