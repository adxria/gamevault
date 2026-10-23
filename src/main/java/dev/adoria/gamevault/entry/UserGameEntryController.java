package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.entry.dto.UserGameEntryRequest;
import dev.adoria.gamevault.entry.dto.UserGameEntryResponse;
import dev.adoria.gamevault.entry.dto.UserGameEntryUpdateRequest;
import dev.adoria.gamevault.security.GameVaultUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
                                             @AuthenticationPrincipal GameVaultUserDetails currentUser,
                                             @Valid @RequestBody UserGameEntryRequest request) {
        UserGameEntry entry = userGameEntryService.createEntry(userId, currentUser.getUserId(), request.gameId(),
            request.status(), request.score());
        return UserGameEntryResponse.from(entry);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserGameEntryResponse> getEntries(@PathVariable Long userId,
                                                  @AuthenticationPrincipal GameVaultUserDetails currentUser) {
        return userGameEntryService.getEntriesForUser(userId, currentUser.getUserId());
    }

    @PutMapping("/{entryId}")
    public UserGameEntryResponse updateEntry(@PathVariable Long entryId, @PathVariable Long userId,
                                             @AuthenticationPrincipal GameVaultUserDetails currentUser,
                                             @Valid @RequestBody UserGameEntryUpdateRequest request) {
        return userGameEntryService.updateEntry(entryId, userId, currentUser.getUserId(), request.status(),
            request.score());
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long entryId, @PathVariable Long userId,
                                            @AuthenticationPrincipal GameVaultUserDetails currentUser) {
        userGameEntryService.deleteEntry(entryId, userId, currentUser.getUserId());
        return ResponseEntity.noContent().build();
    }
}