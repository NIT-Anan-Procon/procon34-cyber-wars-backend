package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Challenges;
import com.example.procon34_CYBER_WARS_backend.mapper.ChallengesMapper;
import com.example.procon34_CYBER_WARS_backend.pojo.Vulnerability;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChallengesRepository {

    private final ChallengesMapper challengesMapper;

    // 脆弱性取得 by フラグ
    public Challenges fetchVulnerabilityByFlag(final int challengeId, final String flag) {
        return challengesMapper.fetchVulnerabilityByFlag(challengeId, flag);
    }

    // 脆弱性取得
    public List<Vulnerability> fetchVulnerabilities(final int challengeId) {
        return challengesMapper.fetchVulnerabilities(challengeId);
    }

}
