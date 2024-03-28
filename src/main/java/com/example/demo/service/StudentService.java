package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentResponseDto;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.utils.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentResponseDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(studentMapper::mapToStudentResponseDto).collect(Collectors.toList());
    }

    public List<Student> findByFirstname(String firstname) {
        return studentRepository.findByFirstname(firstname);
    }

    public Optional<Student> findById(int id) {
        return studentRepository.findById(id);
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
