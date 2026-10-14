package dev.adoria.gamevault.entry;

import dev.adoria.gamevault.exception.DuplicateEntryException;
import dev.adoria.gamevault.exception.ResourceNotFoundException;
import dev.adoria.gamevault.game.Game;
import dev.adoria.gamevault.game.GameRepository;
import dev.adoria.gamevault.user.User;
import dev.adoria.gamevault.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
