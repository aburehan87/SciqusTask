package com.example.Sciqus_Task;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", nullable = false) // Ensure it cannot be null
    private String name;

    @Column(name = "email", nullable = false, unique = true) // Unique and cannot be null  // Ensure email is unique
    private String email;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true) // Make it nullable
    private Course course;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

