import java.util.HashMap;
import java.util.Map;

public enum GameManager {
    INSTANCE;

    private final Map<Integer, Game> games = new HashMap<>();

    public void addGame(String title) {
        Game game = new Game(title);
        this.games.put(game.getId(), game);
    }

    public void removeGame(int id) {
        this.games.remove(id);
    }

    public void editGame(int id, String newTitle) {
        this.games.get(id).setTitle(newTitle);
    }

    public void listGames() {
        System.out.println("Format");
        System.out.println("{ID} Game Title");
        System.out.println("-------------------");
        this.games.forEach((uniqueId, game) ->
                System.out.println("{" + uniqueId + "} " + game.getTitle()));
    }
}