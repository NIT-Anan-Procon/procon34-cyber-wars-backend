package jp.ac.anan.procon.cyber_wars.domain.dto.game;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class FetchStartTimeResponse {

  private final Timestamp startTime;
}
