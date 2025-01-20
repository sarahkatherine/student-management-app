// Importing React for creating the component
import React from "react";

// Importing CSS styles specific to the App component
import './App.css';

// Importing the StudentList component to display a list of students
import StudentList from "./components/StudentList/StudentList";

// Importing the StudentGradeSearch component to search for a student's grade in a specific course
import StudentGradeSearch from "./components/StudentGradeSearch/StudentGradeSearch";

// Importing the AddCourse component to allow adding new courses
import AddCourse from "./components/AddCourse/AddCourse";

// Importing the AddStudent component to allow adding new students
import AddStudent from "./components/AddStudent/AddStudent";

/**
 * The main App component for rendering the Student Application.
 * This component serves as the root component and includes all major features of the app.
 */
function App() {
  return (
    // The main container for the application
    <div className="App">
      {/* Heading for the application */}
      <h1>Welcome to the Student App</h1>
      
      {/* Component to display the list of students */}
      <StudentList />
      
      {/* Component to search for a student's grade in a specific course */}
      <StudentGradeSearch />
      
      {/* Component to add a new course */}
      <AddCourse />
      
      {/* Component to add a new student */}
      <AddStudent />
    </div>
  );
}

// Exporting the App component as the default export
export default App;
