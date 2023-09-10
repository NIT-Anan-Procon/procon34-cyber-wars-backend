package jp.ac.anan.procon.CYBER_WARS.dto.room;

import lombok.Data;

@Data
public class FetchInformationResponse {

  private final String opponentName;

  private final boolean host;

  private final boolean started;
}
