package dev.adoria.gamevault.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record GameRequest(@NotBlank String title, @NotNull LocalDate releaseDate) {
}