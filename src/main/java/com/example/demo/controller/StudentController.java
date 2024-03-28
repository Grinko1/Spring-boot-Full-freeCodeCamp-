package com.example.demo.controller;

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
    public List<Student> findAll(){
        return studentService.findAll();
    }
    @GetMapping("/search/{name}")
    public List<Student> findByFirstname(@PathVariable("name") String name){
        return studentService.findByFirstname(name);
    }
    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") int id){
        return studentService.findById(id).orElse(null);
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
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") int id){
        studentService.deleteById(id);
        return "Deleted";
    }

}
