package jp.ac.anan.procon.cyber_wars.domain.dto.game.defense;

import jakarta.validation.constraints.NotBlank;

public record SendCodeRequest(@NotBlank String code) {}
