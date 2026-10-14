package dev.adoria.gamevault.entry.dto;

import dev.adoria.gamevault.entry.GameStatus;
import dev.adoria.gamevault.entry.UserGameEntry;

public record UserGameEntryResponse(Long id, Long gameId, GameStatus status, Double score) {
    public static UserGameEntryResponse from(UserGameEntry uge) {
        return new UserGameEntryResponse(uge.getId(), uge.getGameId(), uge.getStatus(), uge.getScore());
    }
}