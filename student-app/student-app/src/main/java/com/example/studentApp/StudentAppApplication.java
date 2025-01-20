package com.example.studentApp;

import org.springframework.boot.SpringApplication;  // Class for running the Spring Boot application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Annotation for auto-configuration

/**
 * Entry point for the StudentApp Spring Boot application.
 *
 * Responsibilities:
 * - Initializes the Spring application context.
 * - Scans the base package for components, configurations, and Spring Beans.
 */
@SpringBootApplication
public class StudentAppApplication {

	/**
	 * The main method, which serves as the entry point for the Spring Boot application.
	 *
	 * @param args Command-line arguments that can be passed to the application.
	 */
	public static void main(String[] args) {
		// Runs the application, bootstrapping Spring and initializing all components
		SpringApplication.run(StudentAppApplication.class, args);
	}

}
