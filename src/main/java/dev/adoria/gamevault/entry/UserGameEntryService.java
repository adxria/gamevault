package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.entry.dto.UserGameEntryResponse;
import dev.adoria.gamevault.exception.DuplicateEntryException;
import dev.adoria.gamevault.exception.ResourceNotFoundException;
import dev.adoria.gamevault.game.Game;
import dev.adoria.gamevault.game.GameRepository;
import dev.adoria.gamevault.user.User;
import dev.adoria.gamevault.user.UserRepository;
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

    @Transactional
    public UserGameEntry createEntry(Long userId, Long gameId, GameStatus status, Double score) {
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
    public List<UserGameEntryResponse> getEntriesForUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id " + userId + " not found");
        }

        return userGameEntryRepository.findByUser_Id(userId).stream().map(UserGameEntryResponse::from).toList();
    }

    @Transactional
    public UserGameEntryResponse updateEntry(Long entryId, Long userId, GameStatus status, Double score) {
        UserGameEntry entry = userGameEntryRepository.findByIdAndUser_Id(entryId, userId).orElseThrow(()
            -> new ResourceNotFoundException("Entry with id " + entryId + " not found for user " + userId));
        entry.setStatus(status);
        entry.setScore(score);
        return UserGameEntryResponse.from(entry);
    }

    @Transactional
    public void deleteEntry(Long entryId, Long userId) {
        UserGameEntry entry = userGameEntryRepository.findByIdAndUser_Id(entryId, userId).orElseThrow(()
            -> new ResourceNotFoundException("Entry with id " + entryId + " not found for user " + userId));

        userGameEntryRepository.delete(entry);
    }
}