// Package declaration to group related classes for the student application.
package com.example.studentApp.controller;

// Importing the Grade model to represent grades in this controller.
import com.example.studentApp.model.Grade;
// Importing the GradeService, which contains the business logic for handling grades.
import com.example.studentApp.service.GradeService;
// Importing ResponseEntity to standardize HTTP responses.
import org.springframework.http.ResponseEntity;
// Importing annotations for REST controller functionalities.
import org.springframework.web.bind.annotation.*;

// Importing List to handle collections of Grade objects.
import java.util.List;

// Annotation to declare this class as a REST controller in the Spring Framework.
// This means it can handle HTTP requests and return HTTP responses in JSON format.
@RestController
// Specifies the base URL path for all endpoints in this controller.
@RequestMapping("/api/grades")
public class GradeController {

    // Declares a final reference to the GradeService, ensuring this dependency cannot be reassigned.
    private final GradeService gradeService;

    // Constructor-based dependency injection for the GradeService.
    // Spring automatically provides an instance of GradeService when initializing this controller.
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    /**
     * Adds a new grade to the system.
     * HTTP Method: POST
     * Endpoint: /api/grades
     * Request Body: JSON representation of a Grade object.
     * Response: Returns the added Grade object in the HTTP response.
     * Example JSON to test:
     * {
     *   "letter": "A",
     *   "score": 4.0
     * }
     */
    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) {
        // Calls the service layer to add the grade, encapsulating business logic.
        Grade savedGrade = gradeService.addGrade(grade);
        // Wraps the saved grade in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(savedGrade);
    }

    /**
     * Retrieves all grades from the system.
     * HTTP Method: GET
     * Endpoint: /api/grades
     * Response: A list of Grade objects in JSON format.
     */
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        // Calls the service layer to fetch all grades.
        List<Grade> grades = gradeService.getAllGrades();
        // Wraps the list of grades in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(grades);
    }
}
