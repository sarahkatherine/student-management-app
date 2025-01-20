// Package declaration organizes this repository in the project structure.
package com.example.studentApp.repository;

// Import statements for required Spring Data JPA classes and annotations.
import com.example.studentApp.model.Course; // Importing the Course entity.
import org.springframework.data.jpa.repository.JpaRepository; // Enables CRUD and JPA operations.

import com.example.studentApp.model.Grade; // Ensure Grade model is imported for any grade-related logic (optional here).
import org.springframework.data.jpa.repository.Query; // Enables the use of custom JPQL/SQL queries.
import org.springframework.data.repository.query.Param; // Allows the use of parameters in custom queries.

import java.util.List; // Java utility for handling collections.

// Repository interface for the Course entity, extending JpaRepository to inherit JPA functionality.
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * Finds all courses associated with a specific student by their ID.
     *
     * Method:
     * - Spring Data JPA derives the SQL query based on the method name (`findByStudentId`).
     * - Retrieves a list of courses where the student ID matches the provided parameter.
     *
     * Example Usage:
     * - Used to fetch all courses a student is enrolled in.
     *
     * @param studentId the ID of the student whose courses are to be retrieved.
     * @return a list of Course objects.
     */
    List<Course> findByStudentId(Long studentId);

    /**
     * Finds a specific course by student ID and course name.
     *
     * Method:
     * - Spring Data JPA derives the query using the method name (`findByStudentIdAndName`).
     * - Looks up a single course that matches both the provided student ID and course name.
     *
     * Example Usage:
     * - Used to find the grade for a specific course of a student.
     *
     * @param studentId the ID of the student.
     * @param courseName the name of the course.
     * @return a single Course object matching the criteria.
     */
    Course findByStudentIdAndName(Long studentId, String courseName);
}
