// Importing necessary modules from React
import React, { useState } from "react";

// Importing CSS styles for the StudentGradeSearch component
import './StudentGradeSearch.css';

/**
 * Functional component to search for a student's grade in a specific course.
 * Allows users to input a student ID and course name, and fetches the grade from the backend.
 */
const StudentGradeSearch = () => {
  // State variables to manage user inputs, grade results, and error messages
  const [studentId, setStudentId] = useState(""); // Stores the student ID input
  const [courseName, setCourseName] = useState(""); // Stores the course name input
  const [grade, setGrade] = useState(null); // Stores the fetched grade result
  const [error, setError] = useState(""); // Stores error messages to display to the user

  /**
   * Fetches the grade for a given student ID and course name.
   * Handles input validation, API calls, and error handling.
   */
  const fetchGrade = () => {
    // Validate that both inputs are provided
    if (!studentId || !courseName) {
      setError("Please provide both Student ID and Course Name."); // Displays an error for missing inputs
      return; // Exit the function early if validation fails
    }

    // Send a GET request to the backend API to fetch the grade
    fetch(`http://localhost:8080/api/students/${studentId}/courses/${courseName}/grade`)
      .then((response) => {
        if (!response.ok) {
          // If the response is not successful, throw an error
          throw new Error("Unable to fetch grade. Please check the inputs.");
        }
        return response.json(); // Parse the response as JSON
      })
      .then((data) => {
        setGrade(data); // Stores the fetched grade in the `grade` state
        setError(""); // Clears any previous error messages
      })
      .catch((err) => {
        // Handle errors from the API or network
        setError(err.message); // Sets an error message for the user
        setGrade(null); // Clears any previously stored grade
      });
  };

  // Render the StudentGradeSearch component's user interface
  return (
    <div className="student-grade-search">
      {/* Section heading for the grade search functionality */}
      <h2>View Grade by Student and Course</h2>

      {/* Input field for the Student ID */}
      <div>
        <label>
          Student ID:
          <input
            type="text" // Specifies the input type as text
            value={studentId} // Binds the input value to the `studentId` state
            onChange={(e) => setStudentId(e.target.value)} // Updates the `studentId` state when input changes
            placeholder="Enter Student ID" // Provides placeholder text in the input field
          />
        </label>
      </div>

      {/* Input field for the Course Name */}
      <div>
        <label>
          Course Name:
          <input
            type="text" // Specifies the input type as text
            value={courseName} // Binds the input value to the `courseName` state
            onChange={(e) => setCourseName(e.target.value)} // Updates the `courseName` state when input changes
            placeholder="Enter Course Name" // Provides placeholder text in the input field
          />
        </label>
      </div>

      {/* Button to trigger the fetchGrade function */}
      <button onClick={fetchGrade}>Get Grade</button>

      {/* Conditional rendering to display error messages */}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {/* Conditional rendering to display the fetched grade */}
      {grade !== null && (
        <div>
          <h3>Grade:</h3>
          <p><strong>Score:</strong> {grade}</p>
        </div>
      )}
    </div>
  );
};

// Exporting the StudentGradeSearch component as the default export
export default StudentGradeSearch;
