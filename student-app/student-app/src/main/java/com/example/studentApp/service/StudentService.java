package com.example.studentApp.service;

import com.example.studentApp.dto.CourseResponseDTO;
import com.example.studentApp.dto.StudentResponseDTO;
import com.example.studentApp.model.Course;
import com.example.studentApp.model.Student;
import com.example.studentApp.repository.CourseRepository;
import com.example.studentApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to students.
 * Responsibilities:
 * - Managing student data, including adding students and retrieving student details.
 * - Calculating GPAs and retrieving grades for specific courses and students.
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository; // Repository for student data
    private final CourseRepository courseRepository;   // Repository for course data

    /**
     * Constructor for dependency injection.
     *
     * @param studentRepository Repository for interacting with student data.
     * @param courseRepository Repository for interacting with course data.
     */
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * Adds a new student to the database.
     *
     * @param student The Student object to be added.
     * @return The saved Student object.
     */
    public Student addStudent(Student student) {
        return studentRepository.save(student); // Save the student to the database
    }

    /**
     * Retrieves all students with their calculated GPA and associated courses.
     *
     * @return A list of StudentResponseDTOs containing student details, GPAs, and courses.
     */
    public List<StudentResponseDTO> getAllStudentsWithGPA() {
        // Fetch all students from the database
        List<Student> students = studentRepository.findAll();

        // Map each student to a StudentResponseDTO
        return students.stream()
                .map(student -> {
                    // Fetch and map the student's courses to CourseResponseDTOs
                    List<CourseResponseDTO> courses = courseRepository.findByStudentId(student.getId())
                            .stream()
                            .map(course -> new CourseResponseDTO(
                                    course.getName(),
                                    course.getGrade() != null ? course.getGrade().getLetter() : null, // Handle null grades
                                    course.getGrade() != null ? course.getGrade().getScore() : 0.0  // Default score for null grades
                            ))
                            .collect(Collectors.toList());

                    // Create and return the StudentResponseDTO with student details, GPA, and courses
                    return new StudentResponseDTO(
                            student.getId(),
                            student.getName(),
                            student.getEmail(),
                            student.getGPA(),
                            courses
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the GPA for a specific student.
     *
     * @param id The ID of the student.
     * @return The student's GPA, or 0.0 if the student is not found.
     */
    public double getStudentGPA(Long id) {
        return studentRepository.findById(id) // Fetch the student by ID
                .map(Student::getGPA)        // Calculate GPA if the student exists
                .orElse(0.0);                // Default to 0.0 if the student is not found
    }

    /**
     * Retrieves all grades and associated courses for a specific student.
     *
     * @param studentId The ID of the student.
     * @return A StudentResponseDTO containing the student's details and grades.
     */
    public StudentResponseDTO getGradesForStudent(Long studentId) {
        // Fetch and map the student's courses to CourseResponseDTOs
        List<CourseResponseDTO> courses = courseRepository.findByStudentId(studentId).stream()
                .filter(course -> course.getGrade() != null) // Exclude courses without grades
                .map(course -> new CourseResponseDTO(
                        course.getName(),
                        course.getGrade().getLetter(),
                        course.getGrade().getScore()
                ))
                .collect(Collectors.toList());

        // Fetch the student associated with the first course (assuming at least one course exists)
        Student student = courseRepository.findByStudentId(studentId).get(0).getStudent();

        // Return the StudentResponseDTO with student details and courses
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getGPA(),
                courses
        );
    }

    /**
     * Retrieves the grade for a specific course taken by a specific student.
     *
     * @param studentId The ID of the student.
     * @param courseName The name of the course.
     * @return The grade score for the specified course.
     * @throws IllegalArgumentException if the course or grade is not found.
     */
    public double getGradeForCourse(Long studentId, String courseName) {
        // Fetch the course by student ID and course name
        Course course = courseRepository.findByStudentIdAndName(studentId, courseName);
        if (course != null && course.getGrade() != null) {
            return course.getGrade().getScore(); // Return the grade score
        }
        throw new IllegalArgumentException("No grade found for the specified course and student."); // Handle missing data
    }
}
