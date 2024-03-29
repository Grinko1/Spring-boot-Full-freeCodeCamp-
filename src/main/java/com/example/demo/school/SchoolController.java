package com.example.demo.school;

import com.example.demo.school.SchoolDto;
import com.example.demo.school.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    public SchoolDto createSchool(@RequestBody SchoolDto schoolDto) {
       return schoolService.save(schoolDto);
    }
    @GetMapping
    public List<SchoolDto> getAllSchool() {
        return schoolService.findAll();
    }
}
