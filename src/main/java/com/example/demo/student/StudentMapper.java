package com.example.demo.student;

import com.example.demo.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public Student mapToStudent(StudentDto dto) {
        if (dto == null){
            throw new NullPointerException("The studentDto is null");
        }
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
