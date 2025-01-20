// Package declaration to organize this class within the project structure.
package com.example.studentApp.dto;

// Importing List to manage a collection of CourseResponseDTO objects.
import java.util.List;

// A Data Transfer Object (DTO) class for representing a student's details in a structured and simplified manner.
public class StudentResponseDTO {

    // Unique identifier for the student.
    private Long id;

    // The full name of the student.
    private String name;

    // The email address of the student.
    private String email;

    // The Grade Point Average (GPA) of the student, typically used in endpoints that deal with overall performance.
    private double gpa;

    // A list of courses associated with the student, each represented as a CourseResponseDTO.
    // This is particularly useful when returning grades and course details for a specific student.
    private List<CourseResponseDTO> courses;

    /**
     * Constructor to initialize the StudentResponseDTO.
     *
     * @param id       The unique ID of the student.
     * @param name     The name of the student.
     * @param email    The email of the student.
     * @param gpa      The GPA of the student.
     * @param courses  The list of CourseResponseDTO objects representing the student's courses and grades.
     */
    public StudentResponseDTO(Long id, String name, String email, double gpa, List<CourseResponseDTO> courses) {
        this.id = id;               // Set the student ID.
        this.name = name;           // Set the student's name.
        this.email = email;         // Set the student's email.
        this.gpa = gpa;             // Set the student's GPA.
        this.courses = courses;     // Set the list of courses with their respective grades.
    }

    // Getter for the student's ID.
    public Long getId() {
        return id;
    }

    // Setter for the student's ID.
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for the student's name.
    public String getName() {
        return name;
    }

    // Setter for the student's name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the student's email.
    public String getEmail() {
        return email;
    }

    // Setter for the student's email.
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for the student's GPA.
    public double getGpa() {
        return gpa;
    }

    // Setter for the student's GPA.
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Getter for the list of courses and grades associated with the student.
    public List<CourseResponseDTO> getCourses() {
        return courses;
    }

    // Setter for the list of courses and grades associated with the student.
    public void setCourses(List<CourseResponseDTO> courses) {
        this.courses = courses;
    }
}
