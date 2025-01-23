package com.example.Sciqus_Task;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    // Save course
    public Course saveCourse(Course course) {
        // Check if the course already exists based on course name
        Optional<Course> existingCourse = courseRepository.findByCourseName(course.getCourseName());
        if (existingCourse.isPresent()) {
            throw new RuntimeException("A course with this name already exists: " + course.getCourseName());
        }

        return courseRepository.save(course);
    }

    // Find course by ID
    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
    }

    // Fetch all courses
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    // Update course
    @Transactional
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        // Update course details
        course.setCourseName(courseDetails.getCourseName());
        course.setCourseDuration(courseDetails.getCourseDuration());
        course.setCourseCode(courseDetails.getCourseCode());

        return courseRepository.save(course);
    }

    // Delete a course with student dissociation
    @Transactional
    public void deleteCourseWithDissociation(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        // Dissociate students from this course
        List<Student> students = course.getStudents();
        for (Student student : students) {
            student.setCourse(null);
            studentRepository.save(student);  // Save the dissociation
        }

        // Now delete the course
        courseRepository.delete(course);
    }

    // Delete course without dissociation (if no students are enrolled)
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));

        // Optionally check if students are associated with this course before deleting
        if (!course.getStudents().isEmpty()) {
            throw new RuntimeException("Cannot delete course associated with students. Please dissociate students first.");
        }

        courseRepository.delete(course);
    }
}
