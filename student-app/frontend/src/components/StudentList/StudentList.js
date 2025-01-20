// Importing necessary modules from React
import React, { useState, useEffect } from 'react';

// Importing CSS styles specific to the StudentList component
import './StudentList.css';

/**
 * Functional component to display a list of students and their details.
 * Fetches student data from the backend API and renders it in a table format.
 */
const StudentList = () => {
  // State variable to hold the list of students fetched from the backend
  const [students, setStudents] = useState([]); // Initializes an empty array to store student data

  /**
   * Fetches the list of students from the backend API.
   * Updates the `students` state with the fetched data or logs an error if the request fails.
   */
  const fetchStudents = () => {
    fetch('http://localhost:8080/api/students') // Makes a GET request to the backend API
      .then((response) => response.json()) // Parses the response JSON into JavaScript objects
      .then((data) => setStudents(data)) // Updates the state with the fetched student data
      .catch((error) => 
        console.error('Error fetching student data:', error) // Logs any errors to the console for debugging
      );
  };

  /**
   * useEffect hook to fetch student data when the component is first mounted.
   * Ensures the `fetchStudents` function is called only once.
   */
  useEffect(() => {
    fetchStudents(); // Calls the fetchStudents function when the component mounts
  }, []); // Empty dependency array ensures this effect runs only once

  // Render the StudentList component's UI
  return (
    <div className="student-list">
      {/* Section heading for the student list */}
      <h2>Student List</h2>

      {/* Table to display student details */}
      <table>
        <thead>
          {/* Table headers for student information */}
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>GPA</th>
            <th>Courses</th>
          </tr>
        </thead>
        <tbody>
          {/* Map over the students array to render each student's details in a table row */}
          {students.map((student) => (
            <tr key={student.id}> {/* Provides a unique key for each table row using student ID */}
              <td>{student.id}</td> {/* Displays the student ID */}
              <td>{student.name}</td> {/* Displays the student name */}
              <td>{student.email}</td> {/* Displays the student email */}
              <td>{student.gpa.toFixed(2)}</td> {/* Displays the GPA rounded to two decimal places */}
              <td>
                {/* Conditionally render the list of courses or a message if no courses are enrolled */}
                {student.courses.length > 0 ? (
                  <ul>
                    {student.courses.map((course) => (
                      <li key={course.name}>
                        {course.name} - {course.gradeLetter} {/* Displays course name and grade letter */}
                      </li>
                    ))}
                  </ul>
                ) : (
                  <span>No courses enrolled</span> // Message displayed if the student has no courses
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Refresh button to reload the student data */}
      <div className="refresh-container">
        <button onClick={fetchStudents} className="refresh-button">
          Refresh
        </button>
      </div>
    </div>
  );
};

// Exporting the StudentList component as the default export
export default StudentList;
