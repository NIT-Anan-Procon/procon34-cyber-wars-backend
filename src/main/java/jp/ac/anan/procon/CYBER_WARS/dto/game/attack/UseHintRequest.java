package jp.ac.anan.procon.CYBER_WARS.dto.game.attack;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UseHintRequest {

  @NotBlank private final byte vulnerabilityId;
}
