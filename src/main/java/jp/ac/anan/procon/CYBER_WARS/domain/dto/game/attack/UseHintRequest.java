package jp.ac.anan.procon.cyber_wars.domain.dto.game.attack;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UseHintRequest {

  @NotBlank private final byte vulnerabilityId;
}
