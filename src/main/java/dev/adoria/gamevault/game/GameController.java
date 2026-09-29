package dev.adoria.gamevault.game;

import dev.adoria.gamevault.game.dto.GameRequest;
import dev.adoria.gamevault.game.dto.GameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameResponse> getGames() {
        List<Game> gameList = gameService.getAllGames();
        return gameList.stream().map(GameResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGame(@PathVariable Long id) {
        Optional<Game> gameOptional = gameService.getGameById(id);
        return gameOptional
                .map((game) -> ResponseEntity.ok(GameResponse.from(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponse createGame(@RequestBody GameRequest request) {
        Game gameToCreate = new Game(request.title(), request.releaseDate());
        Game savedGame = gameService.createGame(gameToCreate);
        return GameResponse.from(savedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        if (gameService.deleteGame(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable Long id, @RequestBody GameRequest request) {
        Game newData = new Game(request.title(), request.releaseDate());
        Optional<Game> gameOptional = gameService.updateGame(id, newData);

        return gameOptional
                .map(game -> ResponseEntity.ok(GameResponse.from(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}