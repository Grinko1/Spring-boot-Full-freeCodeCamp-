package com.example.demo.student;

import com.example.demo.school.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentResponseDto> findAll() {
        return  studentRepository.findAll().stream().map(studentMapper::mapToStudentResponseDto).collect(Collectors.toList());
    }

    public List<StudentResponseDto> findByFirstname(String firstname) {
       return studentRepository.findByFirstname(firstname).stream().map(studentMapper::mapToStudentResponseDto).collect(Collectors.toList());
    }

    public StudentResponseDto findById(int id) {
        return studentRepository.findById(id).map(studentMapper::mapToStudentResponseDto).orElse(null);

    }

    public StudentResponseDto save(StudentDto studentDto) {
        var student = studentMapper.mapToStudent(studentDto);
        Student studentSaved = studentRepository.save(student);
        return studentMapper.mapToStudentResponseDto(studentSaved);
    }

    public StudentResponseDto update(int id, StudentDto dto) throws Exception {
        Optional<Student> stForUpdate = studentRepository.findById(id);
        if (stForUpdate.isPresent()) {
            Student existingStudent = stForUpdate.get();
            existingStudent.setFirstname(dto.firstname());
            existingStudent.setLastname(dto.lastname());
            existingStudent.setEmail(dto.email());
            existingStudent.setAge(dto.age());
            School school = new School();
            school.setId(dto.schoolId());
            existingStudent.setSchool(school);

            Student student= studentRepository.save(existingStudent);
            return studentMapper.mapToStudentResponseDto(student);
        } else {
            throw new Exception("User with this id doesn't exist");
        }

    }

    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
