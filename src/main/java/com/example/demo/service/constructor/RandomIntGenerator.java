package com.example.demo.service.constructor;

import java.util.Random;

import lombok.Getter;

// 외부 라이브러리 수정할 수 없음
@Getter
public class RandomIntGenerator {
    private final int num;

    public RandomIntGenerator() {
        num = new Random().nextInt(10) + 1; // 1부터 10까지의 숫자 생성
    }

    public static int getRandomInt() {
        return new Random().nextInt(10) + 1;
    }
}
