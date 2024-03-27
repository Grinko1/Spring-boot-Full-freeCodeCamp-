package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }
    public Optional<Student> findById(int id){
        return studentRepository.findById(id);
    }
    public void save(Student student){
        studentRepository.save(student);
    }
    public void update(int id, Student student) throws Exception {
        Optional<Student> stForUpdate = findById(id);
        student.setId(id);
        if(stForUpdate.isPresent()){
            studentRepository.save(student);
        }else{
            throw new Exception("user with this id doesn't exist");
        }

    }
    public void deleteById(int id){
        studentRepository.deleteById(id);
    }
}
