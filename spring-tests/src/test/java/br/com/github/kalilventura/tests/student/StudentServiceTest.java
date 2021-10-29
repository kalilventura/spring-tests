package br.com.github.kalilventura.tests.student;

import br.com.github.kalilventura.tests.student.exception.BadRequestException;
import br.com.github.kalilventura.tests.student.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void shouldGetAllStudents() {
        studentService.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void shouldAddStudent() {
        String email = "john@gmail.com";
        Student student = new Student("John", email, Gender.MALE);

        studentService.addStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertEquals(capturedStudent, student);
    }

    @Test
    void willThrowWhenEmailIsTakenWhenAddStudent() {
        Student student = new Student("John", "john@gmail.com", Gender.MALE);

        when(studentRepository.selectExistsEmail(anyString())).thenReturn(true);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> studentService.addStudent(student));
        assertEquals("Email " + student.getEmail() + " taken", exception.getMessage());

        // Because I throw a BadRequestException I never call the repo method in addStudent method
        verify(studentRepository, never()).save(any());
    }

    @Test
    void shouldDeleteStudent() {
        Long id = 1L;

        when(studentRepository.existsById(anyLong())).thenReturn(true);

        studentService.deleteStudent(id);

        verify(studentRepository).deleteById(id);
    }

    @Test
    void willThrowWhenStudentDoesNotExists() {
        Long id = 1L;
        when(studentRepository.existsById(anyLong())).thenReturn(false);

        StudentNotFoundException exception = assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(id));
        assertEquals("Student with id " + id + " does not exists", exception.getMessage());

        // Because I throw a StudentNotFoundException I never call the repo method in deleteStudent method
        verify(studentRepository, never()).deleteById(any());
    }
}