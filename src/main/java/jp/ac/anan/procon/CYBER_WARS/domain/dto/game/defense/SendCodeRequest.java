package jp.ac.anan.procon.cyber_wars.domain.dto.game.defense;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendCodeRequest {

  @NotBlank private final String code;
}
