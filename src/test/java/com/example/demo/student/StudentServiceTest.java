package com.example.demo.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void should_successfully_save_student() {
//        GIVEN
        StudentDto dto = new StudentDto("name", "lastname", "some@mail.com", 34, 3);
        Student student = new Student("name", "lastname", "some@mail.com", 34);
        Student savedStudent = new Student("name", "lastname", "some@mail.com", 34);
        savedStudent.setId(1);

//        MOCK THE CALLS
        when(mapper.mapToStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedStudent);
        when(mapper.mapToStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("name", "lastname", "some@mail.com", 34, 3, null));
//        WHEN
        StudentResponseDto responseDto = studentService.save(dto);
//        THEN
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());
        assertEquals(dto.age(), responseDto.age());
        assertEquals(dto.schoolId(), responseDto.schoolId());
        verify(mapper, timeout(1)).mapToStudent(dto);
        verify(repository, timeout(1)).save(student);
        verify(mapper, times(1)).mapToStudentResponseDto(savedStudent);
    }
    @Test
    public void should_return_all_students(){
//        GIVEN
        List<Student> students = new ArrayList<>();
        students.add( new Student("one", "lastname", "some@mail.com", 34));


//        MOCK THE CALLS
        when(repository.findAll()).thenReturn(students);
        when(mapper.mapToStudentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("one", "lastname", "some@mail.com", 34, 1, null));
//        WHEN
        List<StudentResponseDto> responseDtoList = studentService.findAll();
//        THEN
        assertEquals(students.size(), responseDtoList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_one_student_by_id(){
        Integer id = 1;
        Student student = new Student("one", "lastname", "some@mail.com", 34);
        student.setId(id);
//        MOCK THE CALLS

        when(repository.findById(id)).thenReturn(Optional.of(student));
        when(mapper.mapToStudentResponseDto(student)).thenReturn(new StudentResponseDto("one", "lastname", "some@mail.com", 34, 1, null));

//        WHEN
        StudentResponseDto responseDto = studentService.findById(id);

//        THEN
        assertEquals(responseDto.firstname(), student.getFirstname());
        assertEquals(responseDto.lastname(), student.getLastname());
        assertEquals(responseDto.email(), student.getEmail());
        assertEquals(responseDto.age(), student.getAge());
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).mapToStudentResponseDto(student);
    }
    @Test
    public void should_find_students_by_name(){
        String name = "name";
        List<Student> students = new ArrayList<>();
        students.add(new Student("name", "lastname", "some@mail.com", 34));

        when(repository.findByFirstname(name)).thenReturn(students);
        when(mapper.mapToStudentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("name", "lastname", "some@mail.com", 34, 1, null));
        var responseDto = studentService.findByFirstname(name);

        assertEquals(students.size(), responseDto.size());
        verify(repository, times(1)).findByFirstname(name);
    }
    @Test
    public void should_delete_student_by_id(){
        Integer idToDelete = 1;

        // When
        studentService.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }

}