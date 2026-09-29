package dev.adoria.gamevault.game.dto;

import java.time.LocalDate;

public record GameRequest(String title, LocalDate releaseDate) {
}