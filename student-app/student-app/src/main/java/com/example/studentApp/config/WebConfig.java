package com.example.studentApp.config;

// Importing the @Configuration annotation from Spring Framework
// This annotation is used to mark a class as a source of bean definitions for the application context.
import org.springframework.context.annotation.Configuration;

// Importing CorsRegistry from Spring Framework
// This class provides methods to define CORS (Cross-Origin Resource Sharing) configurations.
import org.springframework.web.servlet.config.annotation.CorsRegistry;

// Importing the WebMvcConfigurer interface from Spring Framework
// This interface allows customizing Spring MVC's default configurations, including CORS.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to set up CORS (Cross-Origin Resource Sharing) settings for the application.
 * This ensures the frontend can communicate with the backend, especially when hosted on different domains.
 */
@Configuration // Marks this class as a configuration class for Spring, automatically loaded during startup.
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     * Specifies which origins, methods, and credentials are allowed for cross-origin requests.
     *
     * @param registry The CORS registry used to define mappings and rules.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Defines CORS rules for all API endpoints in the application.
        registry.addMapping("/**")
                // Allows requests from the specified frontend origin.
                // In development, this is typically the localhost address where the frontend runs.
                .allowedOrigins("http://localhost:3000")
                // Specifies the HTTP methods permitted for cross-origin requests.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Enables credentials like cookies or session data to be sent with the request.
                .allowCredentials(true);
    }
}
