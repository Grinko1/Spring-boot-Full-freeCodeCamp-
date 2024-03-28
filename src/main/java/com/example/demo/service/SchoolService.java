package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public School save(School school){
       return schoolRepository.save(school);
    }
    public List<School> findAll(){
        return schoolRepository.findAll();
    }
}
