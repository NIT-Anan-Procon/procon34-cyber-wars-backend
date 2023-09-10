package jp.ac.anan.procon.CYBER_WARS.utility.allocations;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.repository.AllocationsRepository;

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
