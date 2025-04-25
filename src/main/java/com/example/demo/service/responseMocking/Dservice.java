package com.example.demo.service.responseMocking;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Dservice {
    private final Eservice eservice;

    public boolean doSomething() {
        if (eservice.getInterface().getMethod1() > 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingTwice() {
        if (eservice.getInterface().getMethod1() > 5) {
            if (eservice.getInterface().getMethod1() > 5) {
                return false;
            } else {
                return true; // 이 부분을 호출하게 만들고 싶다.
            }
        } else {
            return false;
        }
    }
}
