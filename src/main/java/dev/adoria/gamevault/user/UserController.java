package dev.adoria.gamevault.user;

import dev.adoria.gamevault.security.GameVaultUserDetails;
import dev.adoria.gamevault.user.dto.UserRegistrationRequest;
import dev.adoria.gamevault.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        return userService.register(request.username(), request.email(), request.password());
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal GameVaultUserDetails user) {
        return userService.getById(user.getUserId());
    }
}