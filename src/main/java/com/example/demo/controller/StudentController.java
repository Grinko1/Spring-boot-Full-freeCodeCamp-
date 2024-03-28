package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentResponseDto;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponseDto> findAll() {
       return  studentService.findAll();

    }

    @GetMapping("/search/{name}")
    public List<Student> findByFirstname(@PathVariable("name") String name) {
        return studentService.findByFirstname(name);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") int id) {
        return studentService.findById(id).orElse(null);
    }

    @PostMapping
    public StudentResponseDto save(@RequestBody StudentDto dto) {
       return studentService.save(dto);
    }

    @PatchMapping("/{id}")
    public StudentResponseDto update(@PathVariable("id") int id, @RequestBody StudentDto student) throws Exception {
        return studentService.update(id,student);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        studentService.deleteById(id);
        return "Deleted";
    }

}
