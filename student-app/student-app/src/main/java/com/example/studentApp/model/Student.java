// Package declaration organizes this class within the project's structure.
package com.example.studentApp.model;

// Import statements for JSON serialization and JPA annotations.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// The @JsonIgnoreProperties annotation ensures that fields related to Hibernate's lazy-loading mechanisms
// (like hibernateLazyInitializer and handler) are excluded from JSON responses.
// This prevents unnecessary or problematic data from being serialized into JSON.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

// Marks this class as a JPA entity, meaning it is mapped to a database table.
@Entity
public class Student {

    // Specifies the primary key of the entity.
    @Id
    // Indicates that the ID is auto-generated by the database using the identity strategy.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Represents the name of the student.
    private String name;

    // Represents the email address of the student.
    private String email;

    // Establishes a one-to-many relationship between Student and Course entities.
    // The "mappedBy" attribute refers to the "student" field in the Course entity, indicating that
    // this is the inverse side of the relationship.
    @OneToMany(mappedBy = "student")
    private List<Course> courses = new ArrayList<>();

    // --- Getters and Setters ---
    // These methods provide encapsulated access to the fields and allow modifications if needed.

    /**
     * Retrieves the ID of the student.
     * @return the unique identifier of the student.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     * @param id the unique identifier to assign to the student.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the student.
     * @return the name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the student.
     * @param name the name to assign to the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the student.
     * @return the email of the student.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     * @param email the email to assign to the student.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the list of courses associated with the student.
     * @return a list of Course objects.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the list of courses associated with the student.
     * @param courses the list of courses to assign.
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Calculates the GPA of the student based on their courses.
     * @return the GPA as a double (scale 0.0 - 4.0).
     *
     * Explanation:
     * - If the student has no courses, a default GPA of 4.0 is returned (indicating no grades yet).
     * - Otherwise, the method calculates the average score of all grades in the courses.
     * - The GPA is computed by summing the scores of all grades and dividing by the number of courses.
     */
    public double getGPA() {
        // Return default GPA if no courses are available.
        if (courses == null || courses.isEmpty()) return 4.0;

        double total = 0;
        // Loop through each course to sum up the grade scores.
        for (Course course : courses) {
            total += course.getGrade().getScore(); // Add the score of each grade.
        }
        // Return the average score as the GPA.
        return total / courses.size();
    }
}
