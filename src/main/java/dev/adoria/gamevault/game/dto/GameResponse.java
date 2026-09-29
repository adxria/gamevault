package dev.adoria.gamevault.game.dto;

import dev.adoria.gamevault.game.Game;

import java.time.LocalDate;

public record GameResponse(Long id, String title, LocalDate releaseDate) {
    public static GameResponse from(Game game) {
        return new GameResponse(game.getId(), game.getTitle(), game.getReleaseDate());
    }
}