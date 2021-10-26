package br.com.github.kalilventura.tests.student;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
