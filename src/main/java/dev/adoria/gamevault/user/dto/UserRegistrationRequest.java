package dev.adoria.gamevault.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
    @NotBlank @Size(min = 3, max = 32) @Pattern(regexp = "[A-Za-z0-9_]+") String username,
    @NotBlank @Email @Size(max = 254) String email,
    @NotBlank @Size(min = 8) String password) {
}
