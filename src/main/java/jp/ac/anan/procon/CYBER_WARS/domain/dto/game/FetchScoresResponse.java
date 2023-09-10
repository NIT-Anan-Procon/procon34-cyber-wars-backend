package jp.ac.anan.procon.cyber_wars.domain.dto.game;

import lombok.Data;

@Data
public class FetchScoresResponse {

  private final short scores[];
}
