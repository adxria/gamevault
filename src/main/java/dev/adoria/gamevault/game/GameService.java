package dev.adoria.gamevault.game;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public boolean deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            return false;
        }
        gameRepository.deleteById(id);
        return true;
    }

    public Optional<Game> updateGame(Long id, Game request) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isEmpty()) {
            return Optional.empty();
        }

        Game game = gameOptional.get();
        game.setTitle(request.getTitle());
        game.setReleaseDate(request.getReleaseDate());
        return Optional.of(gameRepository.save(game));
    }
}