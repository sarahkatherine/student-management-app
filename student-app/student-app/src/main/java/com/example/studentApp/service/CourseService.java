package com.example.studentApp.service;

import com.example.studentApp.dto.CourseResponseDTO;
import com.example.studentApp.model.Course;
import com.example.studentApp.model.Grade;
import com.example.studentApp.model.Student;
import com.example.studentApp.repository.CourseRepository;
import com.example.studentApp.repository.GradeRepository;
import com.example.studentApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to courses.
 *
 * Responsibilities:
 * - Manage courses, including adding and retrieving courses.
 * - Interact with repositories to fetch and save data.
 * - Convert entities to DTOs when needed for better abstraction.
 */
@Service
public class CourseService {
    // Dependency for interacting with the Course table in the database
    private final CourseRepository courseRepository;
    // Dependency for interacting with the Student table in the database
    private final StudentRepository studentRepository;
    // Dependency for interacting with the Grade table in the database
    private final GradeRepository gradeRepository;

    /**
     * Constructor for dependency injection.
     * @param courseRepository Repository for managing course data.
     * @param studentRepository Repository for managing student data.
     * @param gradeRepository Repository for managing grade data.
     */
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    /**
     * Adds a new course to the database.
     *
     * @Transactional ensures that the persistence context is active throughout the method's execution.
     * This is critical for fetching and associating related entities (Student, Grade).
     *
     * Steps:
     * 1. Fetch the Student entity by ID.
     * 2. Fetch the Grade entity by ID.
     * 3. Associate the fetched Student and Grade with the Course entity.
     * 4. Save the Course entity to the database.
     *
     * @param course The Course object to be added.
     * @return The saved Course object.
     */
    @Transactional
    public Course addCourse(Course course) {
        // Fetch the student from the database or throw an exception if not found
        Student student = studentRepository.findById(course.getStudent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Fetch the grade from the database or throw an exception if not found
        Grade grade = gradeRepository.findById(course.getGrade().getId())
                .orElseThrow(() -> new IllegalArgumentException("Grade not found"));

        // Associate the fetched student and grade with the course
        course.setStudent(student);
        course.setGrade(grade);

        // Save the course to the database and return the saved entity
        return courseRepository.save(course);
    }

    /**
     * Retrieves all courses from the database and maps them to CourseResponseDTOs.
     *
     * Purpose:
     * - Fetch all courses for listing or other purposes.
     * - Convert the Course entities to DTOs to abstract unnecessary fields.
     *
     * Steps:
     * 1. Retrieve all Course entities from the database using the repository.
     * 2. Stream the list of courses and map each Course entity to a CourseResponseDTO.
     * 3. Collect the DTOs into a list and return it.
     *
     * @return A list of CourseResponseDTOs representing all courses.
     */
    public List<CourseResponseDTO> getAllCourses() {
        // Retrieve all courses from the database
        List<Course> courses = courseRepository.findAll();

        // Convert each Course to a CourseResponseDTO and collect the results in a list
        return courses.stream()
                .map(course -> new CourseResponseDTO(course)) // Mapping Course to CourseResponseDTO
                .collect(Collectors.toList());
    }
}
