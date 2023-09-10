package jp.ac.anan.procon.cyber_wars.domain.dto.game.defense;

import lombok.Data;

@Data
public class FetchCodeResponse {

  private final String path;

  private final String code;
}
