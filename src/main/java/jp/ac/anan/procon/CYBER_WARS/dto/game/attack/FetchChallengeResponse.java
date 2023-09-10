package jp.ac.anan.procon.CYBER_WARS.dto.game.attack;

import java.util.List;

import jp.ac.anan.procon.CYBER_WARS.pojo.Vulnerability;
import lombok.Data;

@Data
public class FetchChallengeResponse {

    private final String path;

    private final List<Vulnerability> vulnerabilities;

}
