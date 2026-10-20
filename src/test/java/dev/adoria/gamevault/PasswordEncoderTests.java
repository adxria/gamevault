package dev.adoria.gamevault;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordEncoderTests {
    @Test
    void testPasswordMatchesStoredHash() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String rawPassword = "password";
        String hashedPassword = encoder.encode(rawPassword);
        boolean matches = encoder.matches(rawPassword, hashedPassword);
        assertTrue(matches);
    }

    @Test
    void incorrectPasswordDoesNotMatch() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String rawPassword = "password";
        String hashedPassword = encoder.encode(rawPassword);
        boolean matches = encoder.matches("incorrectPassword", hashedPassword);
        assertFalse(matches);
    }
}