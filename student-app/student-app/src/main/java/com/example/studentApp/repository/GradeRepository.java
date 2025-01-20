// Package declaration organizes this repository in the project structure.
package com.example.studentApp.repository;

// Import statements for required Spring Data JPA classes and annotations.
import com.example.studentApp.model.Grade; // Importing the Grade entity.
import org.springframework.data.jpa.repository.JpaRepository; // Enables CRUD and JPA operations.

/**
 * Repository interface for the Grade entity, extending JpaRepository.
 *
 * Purpose:
 * - Provides built-in CRUD functionality for the `Grade` entity.
 * - Can be extended with custom queries if additional functionality is needed.
 *
 * This interface eliminates the need for boilerplate code to interact with the database.
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {

    /**
     * Additional queries can be added here if needed in the future.
     * For example:
     * - To find a grade by its letter or range of scores.
     * - To retrieve aggregated grade data for reporting purposes.
     */
}
