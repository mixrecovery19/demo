package com.bit235.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    @Test
    void shouldMatchCorrectPassword() {

        PasswordEncoder passwordEncoder =
                new BCryptPasswordEncoder();

        String rawPassword =
                "mypassword123";

        String hashedPassword =
                passwordEncoder.encode(
                        rawPassword
                );

        boolean matches =
                passwordEncoder.matches(
                        "mypassword123",
                        hashedPassword
                );
System.out.println("RAW PASSWORD: " + rawPassword);
System.out.println("HASHED PASSWORD: " + hashedPassword);
System.out.println("DOES IT MATCH? " + matches);
System.out.println(
        "******** TEST OUTPUT ********"
);
        assertTrue(matches);
    }
}