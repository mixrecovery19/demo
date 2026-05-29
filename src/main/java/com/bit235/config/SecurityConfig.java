package com.bit235.config;
/* Spring imports used for configuration.
 * 
 * @Configuration tells Spring that this class
 * contains setup/configuration code.
 * 
 * @Bean tells Spring to create and manage
 * an object for us automatically.*/ 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 /* BCryptPasswordEncoder is a built-in Spring
 * security class used to HASH passwords.
 * 
 * Hashing means converting a password into
 * a secure unreadable format before storing
 * it in the database.
 * 
 * Example:
 * password "123"
 * becomes something like:
 * $2a$10$Iz3HbIZnLcDi83uQ.0rjw...
 * 
 * Importantly:
 * - hashing is one-way
 * - the original password cannot be reversed
 * - Spring later compares passwords using
 *   passwordEncoder.matches()*/ 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/* PasswordEncoder is an interface.
 * 
 * Think of an interface like a contract.
 * It defines methods that password encoders
 * should follow. 
 * BCryptPasswordEncoder is one implementation
 * of the PasswordEncoder interface. * 
 * Why use the interface?
 * Interfaces allow for flexibility, giving some substance, something we can adapt to our needs.
 * Later we could switch to another encoder
 * without changing large amounts of code.*/ 
import org.springframework.security.crypto.password.PasswordEncoder;
/* @Configuration
* This tells Spring Boot:
 * 
 * "This class contains application setup
 * and configuration information."
 * 
 * During application startup, Spring scans
 * for classes marked with @Configuration
 * and automatically loads them.
 * 
 * In this case, we are configuring
 * password security.*/ 
@Configuration
public class SecurityConfig {

    /*
     * @Bean
     * -----------------------------
     * This tells Spring:
     * 
     * "Create this object once and manage
     * it automatically."
     * 
     * Spring stores the object inside its
     * IoC (Inversion of Control) Container.
     * 
     * That means anywhere in the application
     * we can request a PasswordEncoder and
     * Spring will automatically provide it.
     * 
     * Example:
     * 
     * private final PasswordEncoder
     *     passwordEncoder;
     * 
     * Spring automatically injects it
     * into Services or Controllers.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        /*BCryptPasswordEncoder - interface implementation, in effect BCrypt is a interface to handle hashing of any text, used for passwords.        
         * Creates a BCrypt password encoder.         * 
         * BCrypt is considered secure because:
         * 
         * 1. It hashes passwords rather than
         *    storing plain text.         * 
         * 2. It automatically adds SALT.
         *    Salt is random extra data added
         *    to passwords before hashing.
         * 
         *    This means:
         * 
         *    password "123"
         *    from User A
         * 
         *    and password "123"
         *    from User B
         * 
         *    produce DIFFERENT hashes.
         * 
         * 3. It is intentionally slow,
         *    making brute force attacks
         *    much harder.
         * 
         * In a Spring Boot login system:
         * 
         * Registration:
         * raw password
         * → BCrypt encode()
         * → saved to database
         * 
         * Login:
         * entered password
         * → matches()
         * → compare against stored hash
         * 
         * Example:         * 
         * String encoded =
         *     passwordEncoder
         *         .encode("123");
         * 
         * boolean correct =
         *     passwordEncoder.matches(
         *         "123",
         *         storedPassword
         *     );
         */
        return new BCryptPasswordEncoder();
    }
}