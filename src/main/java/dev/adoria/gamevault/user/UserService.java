package dev.adoria.gamevault.user;

import dev.adoria.gamevault.exception.DuplicateUserException;
import dev.adoria.gamevault.exception.InvalidPasswordException;
import dev.adoria.gamevault.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    public UserResponse register(String username, String email, String rawPassword) {
        if (rawPassword.getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new InvalidPasswordException("Password must not exceed 72 UTF-8 bytes");
        }

        String normalizedUsername = User.normalize(username);
        String normalizedEmail = User.normalize(email);
        if (userRepository.existsByUsername(normalizedUsername)) {
            throw new DuplicateUserException("Username " + normalizedUsername + " is already in use");
        }

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateUserException("Email " + normalizedEmail + " is already in use");
        }
        User savedUser = userRepository.save(new User(normalizedUsername, username, normalizedEmail,
            encoder.encode(rawPassword)));
        return UserResponse.from(savedUser);
    }
}
