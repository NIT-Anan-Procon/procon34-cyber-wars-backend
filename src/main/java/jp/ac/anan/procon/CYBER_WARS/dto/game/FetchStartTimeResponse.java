package jp.ac.anan.procon.CYBER_WARS.dto.game;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class FetchStartTimeResponse {

  private final Timestamp startTime;
}
