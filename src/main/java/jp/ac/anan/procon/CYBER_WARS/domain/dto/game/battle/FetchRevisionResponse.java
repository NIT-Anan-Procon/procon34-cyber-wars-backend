package jp.ac.anan.procon.cyber_wars.domain.dto.game.battle;

import lombok.Data;

@Data
public class FetchRevisionResponse {

  private final String path;

  private final String code;
}
