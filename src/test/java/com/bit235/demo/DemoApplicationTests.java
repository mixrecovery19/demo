package com.bit235.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * UNIQUE Spring Boot Test Class
 *
 * This class is used to verify that the Spring Boot application context
 * loads correctly without errors.
 *
 * @SpringBootTest:
 * - Starts the full Spring Boot application (similar to running the main class)
 * - Loads all beans (controllers, services, repositories, etc.)
 * - Applies configuration (application.properties, auto-configuration)
 *
 * Purpose:
 * - Acts as a "sanity check" to ensure the application can start
 * - Helps detect issues such as:
 *      • Incorrect package structure (component scanning fails)
 *      • Missing or misconfigured beans
 *      • Dependency injection errors
 *      • Configuration problems
 *
 * Note:
 * - This is NOT testing business logic or endpoints
 * - It only verifies that the application starts successfully
 *
 * When it runs:
 * - During 'mvn test' or 'mvn clean install'
 * - Automatically as part of the test lifecycle
 */

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // If the application context fails to start,
        // this test will automatically fail.
        // No code is needed here — successful startup = test passes.
    }

}