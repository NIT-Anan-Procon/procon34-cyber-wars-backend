package com.example.procon34_CYBER_WARS_backend.utilities;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Random4DigitNumberGenerator {

    // ランダム4桁数生成
    public short generateRandom4DigitNumber() {
        Random random = new Random();
        return (short) (random.nextInt(9000) + 1000);
    }

}
