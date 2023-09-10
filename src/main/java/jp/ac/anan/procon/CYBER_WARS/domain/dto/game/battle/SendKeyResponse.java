package jp.ac.anan.procon.cyber_wars.domain.dto.game.battle;

import lombok.Data;

@Data
public class SendKeyResponse {

  private final Boolean valid;

  private final boolean correct;

  private final Byte score;
}
