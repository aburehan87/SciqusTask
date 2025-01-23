package com.example.Sciqus_Task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Register a new student with a course
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student, @RequestParam Long courseId) {
        Student registeredStudent = studentService.saveStudent(student, courseId);
        return ResponseEntity.ok(registeredStudent);
    }

    // Get all students with associated course details
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get a student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long courseId) {
        List<Student> students = studentService.findStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }


    // Update an existing student's details
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    // Disassociate a student from their course
    @PutMapping("/{id}/dissociate-course")
    public ResponseEntity<Student> dissociateCourseFromStudent(@PathVariable Long id) {
        Student updatedStudent = studentService.dissociateStudentFromCourse(id);
        return ResponseEntity.ok(updatedStudent);
    }


    // Delete a student by ID (with restriction on course association)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
