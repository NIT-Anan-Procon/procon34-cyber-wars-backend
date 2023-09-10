package jp.ac.anan.procon.CYBER_WARS.utility.allocations;

import org.springframework.stereotype.Component;

import jp.ac.anan.procon.CYBER_WARS.repository.AllocationsRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OpponentNameFetcher {

    private final AllocationsRepository AllocationsRepository;

    // 相手ユーザー名取得
    public String fetchOpponentName(final int userId, final int roomId) {
        return AllocationsRepository.fetchOpponentName(userId, roomId);
    }

}
