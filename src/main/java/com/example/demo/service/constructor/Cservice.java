package com.example.demo.service.constructor;

import java.util.Random;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Cservice {

    private int privateField;

    @PostConstruct
    private void init() {
        privateField = new Random().nextInt(10) + 1;
    }

    public boolean doSomethingWithConstructor() {
        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        if (randomIntGenerator.getNum() > 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingWithStatic() {
        if (RandomIntGenerator.getRandomInt() > 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingWithPrivateField() {
        if (privateField > 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingWithPrivateMethodWithRandom() {
        return privateMethod(new Random().nextInt(10) + 1);
    }

    private boolean privateMethod(int arg) {
        if (arg > 5) {
            return true;
        } else {
            return false;
        }
    }
}
