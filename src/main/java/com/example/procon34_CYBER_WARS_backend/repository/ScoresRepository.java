package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.mapper.ScoresMapper;

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
