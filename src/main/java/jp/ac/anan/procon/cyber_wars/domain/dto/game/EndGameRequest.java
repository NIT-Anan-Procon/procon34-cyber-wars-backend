package jp.ac.anan.procon.cyber_wars.domain.dto.game;

import jakarta.validation.constraints.NotNull;

public record EndGameRequest(@NotNull boolean rematch) {}