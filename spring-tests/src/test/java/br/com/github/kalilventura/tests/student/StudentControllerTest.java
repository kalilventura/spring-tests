package br.com.github.kalilventura.tests.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService service;
    private StudentController controller;

    @BeforeEach
    void setUp() {
        controller = new StudentController(service);
    }

    @Test
    void shouldGetAllStudents() {
        ResponseEntity<?> response = controller.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldAddStudent() throws Exception {
        Student student = new Student("joshua", "joshua@gmail.com", Gender.MALE);
        ResponseEntity<?> response = controller.addStudent(student);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldDeleteStudent() {
        Long id = 1L;
        ResponseEntity<?> response = controller.deleteStudent(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}