package com.example.demo.student;

import com.example.demo.school.School;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto studentDto = new StudentDto("name", "lastname", "any@mail.com",23,3);
        Student student = mapper.mapToStudent(studentDto);

        assertEquals(studentDto.firstname(), student.getFirstname());
        assertEquals(studentDto.lastname(), student.getLastname());
        assertEquals(studentDto.email(), student.getEmail());
        assertEquals(studentDto.age(), student.getAge());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        Student student = new Student("name", "lastname", "mail@mail.com", 23);

        StudentResponseDto dto = mapper.mapToStudentResponseDto(student);

        assertEquals(student.getFirstname(), dto.firstname());
        assertEquals(student.getLastname(), dto.lastname());
        assertEquals(student.getEmail(), dto.email());
        assertEquals(student.getAge(), dto.age());

    }
    @Test
    public void should_map_studentDto_to_student_when_studentDto_is_null(){
        Student student = mapper.mapToStudent(null);
        assertEquals(student.getFirstname(), "");
        assertEquals(student.getLastname(), "");
    }
    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){
    assertThrows(NullPointerException.class,()-> mapper.mapToStudent(null) );
    }

}