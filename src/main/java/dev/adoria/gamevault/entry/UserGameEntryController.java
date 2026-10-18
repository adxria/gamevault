package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.entry.dto.UserGameEntryRequest;
import dev.adoria.gamevault.entry.dto.UserGameEntryResponse;
import dev.adoria.gamevault.entry.dto.UserGameEntryUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserGameEntryResponse> findAll(@PathVariable Long userId) {
        return userGameEntryService.getEntriesForUser(userId);
    }

    @PutMapping("/{entryId}")
    public UserGameEntryResponse updateEntry(@PathVariable Long entryId, @PathVariable Long userId,
                                             @Valid @RequestBody UserGameEntryUpdateRequest request) {
        return userGameEntryService.updateEntry(entryId, userId, request.status(), request.score());
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long entryId, @PathVariable Long userId) {
        userGameEntryService.deleteEntry(entryId, userId);
        return ResponseEntity.noContent().build();
    }
}
