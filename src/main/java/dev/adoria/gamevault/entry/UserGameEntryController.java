package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.entry.dto.UserGameEntryRequest;
import dev.adoria.gamevault.entry.dto.UserGameEntryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/entries")
public class UserGameEntryController {
    private final UserGameEntryService userGameEntryService;

    public UserGameEntryController(UserGameEntryService userGameEntryService) {
        this.userGameEntryService = userGameEntryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGameEntryResponse createEntry(@PathVariable Long userId,
                                             @Valid @RequestBody UserGameEntryRequest request) {
        UserGameEntry entry = userGameEntryService.createEntry(userId, request.gameId(), request.status(),
            request.score());
        return UserGameEntryResponse.from(entry);
    }
}
