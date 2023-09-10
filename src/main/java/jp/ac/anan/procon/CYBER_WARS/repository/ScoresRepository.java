package jp.ac.anan.procon.CYBER_WARS.repository;

import org.springframework.stereotype.Repository;

import jp.ac.anan.procon.CYBER_WARS.mapper.ScoresMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScoresRepository {

    private final ScoresMapper scoresMapper;

    // スコア取得
    public byte fetchScore(final byte scoreType, final boolean difficult) {
        return scoresMapper.fetchScore(scoreType, difficult);
    }

}
