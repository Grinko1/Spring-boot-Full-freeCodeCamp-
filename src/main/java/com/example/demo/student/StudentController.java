package com.example.demo.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentResponseDto> findAll() {
        return studentService.findAll();

    }

    @GetMapping("/search/{name}")
    public List<StudentResponseDto> findByFirstname(@PathVariable("name") String name) {
        return studentService.findByFirstname(name);
    }

    @GetMapping("/{id}")
    public StudentResponseDto findById(@PathVariable("id") int id) {
        return studentService.findById(id);
    }

    @PostMapping
    public StudentResponseDto save(@Valid @RequestBody StudentDto dto) throws Exception {
        return studentService.save(dto);
    }

    @PatchMapping("/{id}")
    public StudentResponseDto update(@PathVariable("id") int id, @RequestBody StudentDto student) throws Exception {
        return studentService.update(id, student);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        studentService.deleteById(id);
        return "Deleted";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldError = ((FieldError) error).getField();
                    String errMsg = error.getDefaultMessage();
                    errors.put(fieldError, errMsg);
                });
        System.out.println(errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
