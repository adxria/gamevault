package dev.adoria.gamevault.entry.dto;

import dev.adoria.gamevault.entry.GameStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record UserGameEntryUpdateRequest(@NotNull GameStatus status,
                                         @DecimalMin(value = "0.0") @DecimalMax(value = "10.0") Double score) {
}