// Package declaration for organizing the controller class within the student application.
package com.example.studentApp.controller;

// Importing the Student model to represent student-related data.
import com.example.studentApp.model.Student;
// Importing the StudentService to handle business logic related to students.
import com.example.studentApp.service.StudentService;
// Importing ResponseEntity to standardize HTTP responses.
import org.springframework.http.ResponseEntity;
// Importing annotations to map HTTP requests to methods in this controller.
import org.springframework.web.bind.annotation.*;
// Importing the StudentResponseDTO to return structured responses for students.
import com.example.studentApp.dto.StudentResponseDTO;

// Importing List to work with collections of StudentResponseDTO objects.
import java.util.List;

// Annotation to declare this class as a REST controller that handles HTTP requests and returns JSON responses.
@RestController
// Specifies the base URL path for all endpoints in this controller.
@RequestMapping("/api/students")
public class StudentController {

    // Declares a final reference to the StudentService, ensuring it cannot be reassigned.
    private final StudentService studentService;

    // Constructor-based dependency injection for StudentService.
    // Spring will provide an instance of StudentService when initializing this controller.
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Adds a new student to the system.
     * HTTP Method: POST
     * Endpoint: /api/students
     * Request Body: JSON representation of a Student object.
     * Response: Returns the added Student object in the HTTP response.
     * Example JSON to test:
     * {
     *   "name": "John Doe",
     *   "email": "johndoe@example.com"
     * }
     */
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        // Calls the service layer to add a new student.
        Student savedStudent = studentService.addStudent(student);
        // Wraps the saved student in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(savedStudent);
    }

    /**
     * Lists all students along with their GPA.
     * HTTP Method: GET
     * Endpoint: /api/students
     * Response: A list of StudentResponseDTO objects in JSON format, each containing student details and GPA.
     */
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudentsWithGPA() {
        // Calls the service layer to fetch all students with their GPA.
        List<StudentResponseDTO> studentsWithGPA = studentService.getAllStudentsWithGPA();
        // Wraps the list of students in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(studentsWithGPA);
    }

    /**
     * Retrieves the GPA of a specific student.
     * HTTP Method: GET
     * Endpoint: /api/students/{id}/gpa
     * Path Variable: `id` - The ID of the student whose GPA is to be retrieved.
     * Response: Returns the GPA as a double value.
     */
    @GetMapping("/{id}/gpa")
    public ResponseEntity<Double> getStudentGPA(@PathVariable Long id) {
        // Calls the service layer to get the GPA for the student with the given ID.
        double gpa = studentService.getStudentGPA(id);
        // Wraps the GPA in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(gpa);
    }

    /**
     * Retrieves all grades and associated courses for a specific student.
     * HTTP Method: GET
     * Endpoint: /api/students/{studentId}/grades
     * Path Variable: `studentId` - The ID of the student whose grades and courses are to be retrieved.
     * Response: A StudentResponseDTO containing student details and a list of their courses with grades.
     */
    @GetMapping("/{studentId}/grades")
    public ResponseEntity<StudentResponseDTO> getGradesForStudent(@PathVariable Long studentId) {
        // Calls the service layer to get the grades and courses for the specified student.
        StudentResponseDTO studentGrades = studentService.getGradesForStudent(studentId);
        // Wraps the student grades in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(studentGrades);
    }

    /**
     * Retrieves the grade for a specific course taken by a specific student.
     * HTTP Method: GET
     * Endpoint: /api/students/{studentId}/courses/{courseName}/grade
     * Path Variables:
     *   `studentId` - The ID of the student.
     *   `courseName` - The name of the course.
     * Response: Returns the grade for the course as a double value.
     */
    @GetMapping("/{studentId}/courses/{courseName}/grade")
    public ResponseEntity<Double> getGradeByCourse(@PathVariable Long studentId, @PathVariable String courseName) {
        // Calls the service layer to get the grade for the specified course and student.
        double grade = studentService.getGradeForCourse(studentId, courseName);
        // Wraps the grade in a ResponseEntity with HTTP 200 (OK) status and returns it.
        return ResponseEntity.ok(grade);
    }
}
