package com.example.demo.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public SchoolDto mapToSchoolDto(School school) {
        return new SchoolDto(school.getName(), school.getStudents());
    }
    public School mapToSchool(SchoolDto schoolDto) {
        return new School(schoolDto.name());
    }
}
