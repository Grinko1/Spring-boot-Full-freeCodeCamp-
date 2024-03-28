package com.example.demo.utils;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentResponseDto;
import com.example.demo.model.School;
import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student mapToStudent(StudentDto dto) {
        Student student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());
        student.setAge(dto.age());

        var school = new School();

        school.setId(dto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResponseDto mapToStudentResponseDto(Student student) {
        Integer schoolId = null;
        if(student.getSchool() != null){
            schoolId = student.getSchool().getId();
        }

        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail(), student.getAge(),schoolId, student.getStudentProfile());
    }
}
