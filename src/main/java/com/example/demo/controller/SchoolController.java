package com.example.demo.controller;

import com.example.demo.dto.SchoolDto;
import com.example.demo.model.School;
import com.example.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping
    public SchoolDto createSchool(@RequestBody SchoolDto schoolDto) {
        var school = mapToSchool(schoolDto);
        schoolService.save(school);
        return schoolDto;
    }

    private School mapToSchool(SchoolDto schoolDto) {
        return new School(schoolDto.name());
    }

    @GetMapping
    public List<SchoolDto> getAllSchool() {
        return schoolService.findAll().stream().map(this::mapToSchoolDto).collect(Collectors.toList());
    }

    private SchoolDto mapToSchoolDto(School school) {
        return new SchoolDto(school.getName(), school.getStudents());
    }

}
