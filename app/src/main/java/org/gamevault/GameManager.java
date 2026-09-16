package org.gamevault;

import java.util.HashMap;
import java.util.Map;

public enum GameManager {
    INSTANCE;

    private final Map<Integer, Game> gameLibrary = new HashMap<>();

    public void addGame(String title) {
        Game game = new Game(title);
        this.gameLibrary.put(game.getId(), game);
    }

    public void editGame(int id, String newTitle) {
        this.gameLibrary.get(id).setTitle(newTitle);
    }

    public void removeGame(int id) {
        this.gameLibrary.remove(id);
    }

    public Game getGame(int id) {
        return this.gameLibrary.get(id);
    }

    public String listGames() {
        StringBuilder sb = new StringBuilder();
        gameLibrary.forEach((id, game) -> {
            sb.append('{').append(id).append('}').append(' ').append(game.getTitle()).append('\n');
        });

        return sb.toString();
    }
}