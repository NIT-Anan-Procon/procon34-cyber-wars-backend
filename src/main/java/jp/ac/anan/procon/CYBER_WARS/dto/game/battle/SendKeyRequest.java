package jp.ac.anan.procon.CYBER_WARS.dto.game.battle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SendKeyRequest {

    @NotBlank
    @Size(max = 20)
    private final String flag;

}
