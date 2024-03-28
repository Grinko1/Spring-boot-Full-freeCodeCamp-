package com.example.demo.dto;

import com.example.demo.model.Student;

import java.util.List;

public record SchoolDto(
         String name,
         List<Student> students
) {
}
