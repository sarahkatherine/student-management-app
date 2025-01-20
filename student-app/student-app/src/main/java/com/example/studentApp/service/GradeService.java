package com.example.studentApp.service;

import com.example.studentApp.model.Grade;
import com.example.studentApp.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling business logic related to grades.
 *
 * Responsibilities:
 * - Manage grades, including adding and retrieving all grades.
 * - Act as the intermediary between the controller and the repository.
 */
@Service
public class GradeService {
    // Dependency for interacting with the Grade table in the database
    private final GradeRepository gradeRepository;

    /**
     * Constructor for dependency injection.
     * The GradeRepository is injected to allow interaction with the database.
     *
     * @param gradeRepository Repository for managing grade data.
     */
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    /**
     * Adds a new grade to the database.
     *
     * Explanation:
     * - Uses Spring Data JPA's `save()` method to persist the Grade object.
     * - No custom repository method is needed because `save()` is built into `JpaRepository`.
     *
     * @param grade The Grade object to be added.
     * @return The saved Grade object.
     */
    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade); // Persist the grade entity to the database
    }

    /**
     * Retrieves all grades from the database.
     *
     * Explanation:
     * - Uses Spring Data JPA's `findAll()` method to fetch all Grade entities.
     * - No custom repository method is needed because `findAll()` is built into `JpaRepository`.
     *
     * @return A list of all Grade objects.
     */
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll(); // Retrieve all grades from the database
    }
}
