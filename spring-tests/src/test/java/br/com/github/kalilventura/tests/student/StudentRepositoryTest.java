package br.com.github.kalilventura.tests.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
/*
 * In this example I'm testing the repository because I created a custom method with custom query
 */
class StudentRepositoryTest {

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void isShouldCheckIfStudentEmailExists() {
        String email = "john@gmail.com";
        Student student = new Student("John", email, Gender.MALE);

        studentRepository.save(student);

        boolean exists = studentRepository.selectExistsEmail(email);

        assertTrue(exists);
    }

    @Test
    void isShouldCheckIfStudentEmailDoesNotExists() {
        String email = "john@gmail.com";

        boolean exists = studentRepository.selectExistsEmail(email);

        assertFalse(exists);
    }

}