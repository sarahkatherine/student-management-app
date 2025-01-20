package com.example.studentApp.controller;

// Importing necessary classes and annotations for the controller, DTOs, models, and services.
import com.example.studentApp.dto.CourseResponseDTO;
import com.example.studentApp.model.Course;
import com.example.studentApp.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles HTTP requests for managing courses.
 * It provides endpoints to add new courses and retrieve all courses.
 */
@RestController // Marks this class as a RESTful controller, capable of handling web requests and returning JSON responses.
@RequestMapping("/api/courses") // Specifies the base URL for all endpoints in this controller.
public class CourseController {

    // A reference to the service layer, which contains business logic for managing courses.
    private final CourseService courseService;

    /**
     * Constructor for dependency injection.
     * The CourseService is injected here to separate business logic from the controller.
     *
     * @param courseService The service layer for course-related operations.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService; // Assigns the injected service to the local variable.
    }

    /**
     * Endpoint to add a new course.
     * Accepts a Course object in the HTTP request body, saves it via the service layer,
     * and returns the saved course in a standardized DTO format.
     *
     * @param course The course object to be saved (parsed from the JSON request body).
     * @return A ResponseEntity containing a CourseResponseDTO for the saved course.
     */
    @PostMapping // Maps this method to HTTP POST requests for the "/api/courses" endpoint.
    public ResponseEntity<CourseResponseDTO> addCourse(@RequestBody Course course) {
        // Calls the service layer to save the incoming course object.
        Course savedCourse = courseService.addCourse(course);

        // Converts the saved course to a CourseResponseDTO and returns it wrapped in a ResponseEntity.
        return ResponseEntity.ok(new CourseResponseDTO(savedCourse));
    }

    /**
     * Endpoint to retrieve all courses.
     * Fetches all courses from the service layer, maps them to DTOs,
     * and returns them as a JSON array.
     *
     * @return A ResponseEntity containing a list of CourseResponseDTO objects.
     */
    @GetMapping // Maps this method to HTTP GET requests for the "/api/courses" endpoint.
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        // Calls the service layer to retrieve all courses and convert them to DTOs.
        List<CourseResponseDTO> courses = courseService.getAllCourses();

        // Returns the list of CourseResponseDTOs wrapped in a ResponseEntity.
        return ResponseEntity.ok(courses);
    }
}
