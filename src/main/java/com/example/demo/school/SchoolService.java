package com.example.demo.school;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolDto save(SchoolDto dto) {
        var school = schoolMapper.mapToSchool(dto);
        var savedSchool = schoolRepository.save(school);
        return schoolMapper.mapToSchoolDto(savedSchool);
    }

    public List<SchoolDto> findAll() {
        return schoolRepository.findAll().stream().map(schoolMapper::mapToSchoolDto).collect(Collectors.toList());

    }
}
