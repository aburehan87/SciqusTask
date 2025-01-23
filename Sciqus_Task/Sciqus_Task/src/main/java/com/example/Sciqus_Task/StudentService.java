package com.example.Sciqus_Task;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Save student with course association
    @Transactional
    public Student saveStudent(Student student, Long courseId) {
        // Validate that the course exists before saving the student
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new RuntimeException("Course not found with ID: " + courseId);
        }

        // Check if the student email is unique
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new RuntimeException("A student with this email already exists: " + student.getEmail());
        }

        student.setCourse(course.get());
        return studentRepository.save(student);
    }

    // Find student by ID
    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    // Fetch all students with course details
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        return course.getStudents();
    }


    // Update student details
    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // Check if the email is being updated and if the new email already exists
        if (studentDetails.getEmail() != null && !studentDetails.getEmail().equals(student.getEmail())) {
            Optional<Student> existingStudent = studentRepository.findByEmail(studentDetails.getEmail());
            if (existingStudent.isPresent()) {
                throw new RuntimeException("Email already in use: " + studentDetails.getEmail());
            }
            student.setEmail(studentDetails.getEmail());
        }

        // Update the student's name and course
        student.setName(studentDetails.getName());

        // Optionally update course
        if (studentDetails.getCourse() != null) {
            Course course = courseRepository.findById(studentDetails.getCourse().getId())
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + studentDetails.getCourse().getId()));
            student.setCourse(course);
        }

        return studentRepository.save(student);
    }

    // Service method to dissociate a student from their course
    @Transactional
    public Student dissociateStudentFromCourse(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Set the course to null to dissociate the student from the course
        student.setCourse(null);
        return studentRepository.save(student);
    }


    @Transactional
    public void deleteCourseWithDissociation(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        // Dissociate all students from this course
        List<Student> students = course.getStudents();
        for (Student student : students) {
            student.setCourse(null); // Set course to null for each student
            studentRepository.save(student);
        }

        // Now delete the course
        courseRepository.delete(course);
    }


    // Delete student with restriction
    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // If needed, restrict deletion if student is associated with a course
        if (student.getCourse() != null) {
            throw new RuntimeException("Cannot delete student associated with a course. Please dissociate first.");
        }

        studentRepository.delete(student);
    }
}
