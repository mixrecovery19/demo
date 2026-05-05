package com.bit235;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main application class for Spring Boot.
// This is the entry point of the application where execution begins.
// It bootstraps Spring, performs component scanning (controllers, services, etc.),
// and enables auto-configuration based on dependencies.
// Similar to a React root in that it starts the app, but it does NOT control application flow.

@SpringBootApplication
public class SpringtimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringtimeApplication.class, args);
	}

}