package se.lexicon.aman.registeration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.aman.registeration.entity.Student;
import se.lexicon.aman.registeration.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(studentList::add);
        return ResponseEntity.ok(studentList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") String id) {
        System.out.println("ID: " + id);
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent())
            return ResponseEntity.ok(optionalStudent.get());
        else
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        System.out.println("id = " + id);
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();

        }
    }

    @PostMapping("/")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        System.out.println("student = " + student);
        Student res = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student res = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
