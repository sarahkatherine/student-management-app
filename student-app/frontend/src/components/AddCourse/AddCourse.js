// Importing necessary modules and components from React
import React, { useState } from "react";

// Importing CSS styles specific to the AddCourse component
import './AddCourse.css';

/**
 * Functional component for adding a new course.
 * This component manages form inputs and sends data to the backend API.
 */
const AddCourse = () => {
  // State variables to manage user input for course name, student ID, and grade ID
  const [courseName, setCourseName] = useState(""); // Stores the course name entered by the user
  const [studentId, setStudentId] = useState("");  // Stores the student ID entered by the user
  const [gradeId, setGradeId] = useState("");      // Stores the grade ID entered by the user

  /**
   * Handles form submission by sending a POST request to the backend API.
   * Resets the input fields upon success and provides user feedback.
   *
   * @param {Event} e - The form submission event
   */
  const handleAddCourse = (e) => {
    e.preventDefault(); // Prevents the default form submission behavior

    // Object to store the course data to be sent to the backend
    const courseData = {
      name: courseName,            // The course name entered by the user
      student: {
        id: parseInt(studentId),   // Converts student ID from string to an integer
      },
      grade: {
        id: parseInt(gradeId),     // Converts grade ID from string to an integer
      },
    };

    // Making a POST request to save the course data in the backend
    fetch("http://localhost:8080/api/courses", {
      method: "POST", // Specifies the HTTP method as POST
      headers: {
        "Content-Type": "application/json", // Indicates the request body contains JSON
      },
      body: JSON.stringify(courseData), // Converts the courseData object to JSON format
    })
      .then((response) => {
        if (response.ok) {
          // If the response is successful, notify the user and reset the input fields
          alert("Course added successfully!");
          setCourseName(""); // Clears the course name input
          setStudentId("");  // Clears the student ID input
          setGradeId("");    // Clears the grade ID input
        } else {
          // If the response fails, notify the user of the error
          alert("Error adding course. Please try again.");
        }
      })
      .catch((error) => {
        // Handles network or other unexpected errors
        console.error("Error adding course:", error); // Logs the error for debugging
        alert("Failed to add course."); // Alerts the user of the failure
      });
  };

  // Rendering the AddCourse component UI
  return (
    <div className="add-course">
      {/* Header section for the Add Course form */}
      <h2>Add a New Course</h2>

      {/* Form to accept user input for adding a course */}
      <form onSubmit={handleAddCourse}>
        <label>
          Course Name:
          {/* Input field for the course name */}
          <input
            type="text" // Defines the input type as text
            value={courseName} // Binds the input value to the courseName state
            onChange={(e) => setCourseName(e.target.value)} // Updates the state on input change
            required // Makes the input field mandatory
          />
        </label>
        <label>
          Student ID:
          {/* Input field for the student ID */}
          <input
            type="number" // Defines the input type as number
            value={studentId} // Binds the input value to the studentId state
            onChange={(e) => setStudentId(e.target.value)} // Updates the state on input change
            required // Makes the input field mandatory
          />
        </label>
        <label>
          Grade ID:
          {/* Input field for the grade ID */}
          <input
            type="number" // Defines the input type as number
            value={gradeId} // Binds the input value to the gradeId state
            onChange={(e) => setGradeId(e.target.value)} // Updates the state on input change
            required // Makes the input field mandatory
          />
        </label>
        {/* Submit button to add the course */}
        <button type="submit">Add Course</button>
      </form>

      {/* Section providing reference information about grade IDs */}
      <div className="grade-info">
        <h3>Grade ID Reference</h3>
        {/* List of grade ID mappings */}
        <ul>
          <li><strong>1</strong>: A (4.0)</li>
          <li><strong>2</strong>: B (3.0)</li>
          <li><strong>3</strong>: C (2.0)</li>
          <li><strong>4</strong>: D (1.0)</li>
          <li><strong>5</strong>: F (0.0)</li>
        </ul>
      </div>
    </div>
  );
};

// Exporting the AddCourse component as the default export
export default AddCourse;
