package jp.ac.anan.procon.CYBER_WARS.dto.game.attack;

import lombok.Data;

@Data
public class SendKeyResponse {

    private final Boolean valid;

    private final boolean correct;

    private final Byte score;

}
