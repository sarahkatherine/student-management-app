// Package declaration for organizing this class within the application.
package com.example.studentApp.dto;

// Importing the Course model to construct the DTO from a Course object.
import com.example.studentApp.model.Course;

// A Data Transfer Object (DTO) class for representing course details in a structured and simplified manner.
public class CourseResponseDTO {

    // The name of the course.
    private String name;
    // The letter grade for the course (e.g., A, B, C, etc.).
    private String gradeLetter;
    // The numeric grade score for the course (e.g., 4.0 for A, 3.0 for B, etc.).
    private double gradeScore;

    /**
     * Constructor to initialize the DTO using individual field values.
     * This is useful when the data is already available in separate variables.
     * @param name The name of the course.
     * @param gradeLetter The letter grade for the course.
     * @param gradeScore The numeric grade score for the course.
     */
    public CourseResponseDTO(String name, String gradeLetter, double gradeScore) {
        this.name = name;
        this.gradeLetter = gradeLetter;
        this.gradeScore = gradeScore;
    }

    /**
     * Constructor to initialize the DTO using a `Course` object.
     * Extracts the relevant fields from the `Course` object and its related `Grade` object.
     * Handles null grades gracefully to avoid NullPointerException.
     * @param course The `Course` object from which data will be extracted.
     */
    public CourseResponseDTO(Course course) {
        this.name = course.getName(); // Sets the course name.

        // Checks if the course has an associated grade.
        if (course.getGrade() != null) {
            this.gradeLetter = course.getGrade().getLetter(); // Sets the grade letter from the Grade object.
            this.gradeScore = course.getGrade().getScore();   // Sets the grade score from the Grade object.
        } else {
            // If the grade is null, set defaults to handle gracefully.
            this.gradeLetter = null;
            this.gradeScore = 0.0;
        }
    }

    // Getter for the course name.
    public String getName() {
        return name;
    }

    // Setter for the course name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the grade letter.
    public String getGradeLetter() {
        return gradeLetter;
    }

    // Setter for the grade letter.
    public void setGradeLetter(String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    // Getter for the numeric grade score.
    public double getGradeScore() {
        return gradeScore;
    }

    // Setter for the numeric grade score.
    public void setGradeScore(double gradeScore) {
        this.gradeScore = gradeScore;
    }
}
