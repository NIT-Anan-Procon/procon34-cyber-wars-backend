package jp.ac.anan.procon.cyber_wars.domain.dto.game.attack;

import java.util.List;
import jp.ac.anan.procon.cyber_wars.domain.pojo.Vulnerability;
import lombok.Data;

@Data
public class FetchChallengeResponse {

  private final String path;

  private final List<Vulnerability> vulnerabilities;
}
