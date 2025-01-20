// Importing necessary modules from React
import React, { useState } from "react";

// Importing CSS styles for the AddStudent component
import './AddStudent.css';

/**
 * Functional component for adding a new student.
 * Manages form inputs and sends student data to the backend API.
 */
const AddStudent = () => {
  // State variables to manage user input and feedback messages
  const [name, setName] = useState(""); // Stores the student's name input
  const [email, setEmail] = useState(""); // Stores the student's email input
  const [successMessage, setSuccessMessage] = useState(""); // Holds the success message for successful submission
  const [errorMessage, setErrorMessage] = useState(""); // Holds the error message for failed submission

  /**
   * Handles form submission by sending a POST request to the backend API.
   * Resets input fields and displays feedback messages based on the response.
   *
   * @param {Event} e - The form submission event
   */
  const handleSubmit = (e) => {
    e.preventDefault(); // Prevents the default behavior of reloading the page on form submission

    // Object containing the new student's data
    const newStudent = { 
      name,  // Maps the current value of the name state to the object
      email, // Maps the current value of the email state to the object
    };

    // Send a POST request to add the new student to the backend database
    fetch("http://localhost:8080/api/students", {
      method: "POST", // Specifies the HTTP method as POST
      headers: {
        "Content-Type": "application/json", // Indicates the request body format is JSON
      },
      body: JSON.stringify(newStudent), // Converts the student object to JSON format for transmission
    })
      .then((response) => {
        if (response.ok) {
          // If the request is successful, display a success message and reset the form
          setSuccessMessage("Student added successfully!"); // Sets the success message
          setErrorMessage(""); // Clears any previous error messages
          setName(""); // Resets the name input field
          setEmail(""); // Resets the email input field
        } else {
          // If the request fails, display the error message provided by the backend
          response.text().then((text) => setErrorMessage(text)); // Extracts and sets the error message
          setSuccessMessage(""); // Clears any previous success messages
        }
      })
      .catch((error) => {
        // Handles any network errors or unexpected issues
        setErrorMessage("Error adding student: " + error.message); // Sets a generic error message
        setSuccessMessage(""); // Clears any previous success messages
      });
  };

  // Rendering the AddStudent component's user interface
  return (
    <div className="add-student">
      {/* Section heading for the form */}
      <h2>Add New Student</h2>

      {/* Form for adding a student */}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          {/* Input field for the student's name */}
          <input
            type="text" // Defines the input type as text
            value={name} // Binds the input value to the name state
            onChange={(e) => setName(e.target.value)} // Updates the name state when the input changes
            required // Makes the input field mandatory
          />
        </div>
        <div>
          <label>Email:</label>
          {/* Input field for the student's email */}
          <input
            type="email" // Defines the input type as email
            value={email} // Binds the input value to the email state
            onChange={(e) => setEmail(e.target.value)} // Updates the email state when the input changes
            required // Makes the input field mandatory
          />
        </div>
        {/* Submit button for the form */}
        <button type="submit">Add Student</button>
      </form>

      {/* Conditional rendering for success and error messages */}
      {successMessage && <p className="success">{successMessage}</p>} 
      {errorMessage && <p className="error">{errorMessage}</p>} 
    </div>
  );
};

// Exporting the AddStudent component as the default export
export default AddStudent;
