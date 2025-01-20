// Package declaration organizes this repository in the project structure.
package com.example.studentApp.repository;

// Import statements for required Spring Data JPA classes and annotations.
import com.example.studentApp.model.Student; // Importing the Student entity.
import org.springframework.data.jpa.repository.JpaRepository; // Enables CRUD and JPA operations.

/**
 * Repository interface for the Student entity, extending JpaRepository.
 *
 * Purpose:
 * - Provides built-in CRUD functionality for the `Student` entity.
 * - Can be extended with custom queries if additional functionality is needed.
 *
 * This interface abstracts database interactions, reducing boilerplate code.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Placeholder for additional queries if required in the future.
     * For example:
     * - Find students by name.
     * - Fetch students based on email domain for reporting purposes.
     */
}
