package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }
    @PostMapping
    public String save(@RequestBody Student student){
        studentService.save(student);
        return "created";
    }
    @PatchMapping("/{id}")
    public String update (@PathVariable("id") int id, @RequestBody Student student) throws Exception {
        studentService.update(id, student);
        return "updated";
    }
}
