package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.entry.dto.UserGameEntryResponse;
import dev.adoria.gamevault.exception.DuplicateEntryException;
import dev.adoria.gamevault.exception.ResourceNotFoundException;
import dev.adoria.gamevault.game.Game;
import dev.adoria.gamevault.game.GameRepository;
import dev.adoria.gamevault.user.User;
import dev.adoria.gamevault.user.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserGameEntryService {
    private final UserGameEntryRepository userGameEntryRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public UserGameEntryService(UserGameEntryRepository userGameEntryRepository, UserRepository userRepository,
                                GameRepository gameRepository) {
        this.userGameEntryRepository = userGameEntryRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    private void requireOwnership(Long userId, Long authenticatedUserId, String msg) {
        if (!userId.equals(authenticatedUserId)) {
            throw new AccessDeniedException(msg);
        }

    }

    @Transactional
    public UserGameEntry createEntry(Long userId, Long authenticatedUserId, Long gameId, GameStatus status,
                                     Double score) {
        requireOwnership(userId, authenticatedUserId, "You cannot create an entry for another user");

        User user = userRepository.findById(userId).orElseThrow(()
            -> new ResourceNotFoundException("User with id " + userId + " not found"));

        Game game = gameRepository.findById(gameId).orElseThrow(()
            -> new ResourceNotFoundException("Game with id " + gameId + " not found"));

        if (userGameEntryRepository.existsByUser_IdAndGame_Id(userId, gameId)) {
            throw new DuplicateEntryException("Entry with game id " + gameId +
                " for user " + userId + " already exists");
        }

        return userGameEntryRepository.save(new UserGameEntry(user, game, status, score));
    }

    @Transactional(readOnly = true)
    public List<UserGameEntryResponse> getEntriesForUser(Long userId, Long authenticatedUserId) {
        requireOwnership(userId, authenticatedUserId, "You cannot access another user's entries");

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }

        return userGameEntryRepository.findByUser_Id(userId).stream().map(UserGameEntryResponse::from).toList();
    }

    @Transactional
    public UserGameEntryResponse updateEntry(Long entryId, Long userId, Long authenticatedUserId, GameStatus status,
                                             Double score) {
        requireOwnership(userId, authenticatedUserId, "You cannot access another user's entries");

        UserGameEntry entry = userGameEntryRepository.findByIdAndUser_Id(entryId, userId).orElseThrow(()
            -> new ResourceNotFoundException("Entry with id " + entryId + " not found for user " + userId));
        entry.setStatus(status);
        entry.setScore(score);
        return UserGameEntryResponse.from(entry);
    }

    @Transactional
    public void deleteEntry(Long entryId, Long userId, Long authenticatedUserId) {
        requireOwnership(userId, authenticatedUserId, "You cannot access another user's entries");

        UserGameEntry entry = userGameEntryRepository.findByIdAndUser_Id(entryId, userId).orElseThrow(()
            -> new ResourceNotFoundException("Entry with id " + entryId + " not found for user " + userId));

        userGameEntryRepository.delete(entry);
    }
}