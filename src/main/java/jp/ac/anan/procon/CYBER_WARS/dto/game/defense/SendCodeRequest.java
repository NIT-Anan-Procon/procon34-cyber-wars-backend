package jp.ac.anan.procon.CYBER_WARS.dto.game.defense;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendCodeRequest {

    @NotBlank
    private final String code;

}
