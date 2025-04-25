package com.example.demo.service.anyExample;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Complex;

@Service
public class Bservice {
    public boolean doSomething(Complex complex) { // 너무 복잡한 객체를 받는 경우 test fixture를 만들기 힘들다.
        return true;
    }

    public boolean doSomethingArgTwo(Complex complex, int i) {
        return true;
    }

    public boolean doSomethingArgNull(Complex complex, Long i) {
        return true;
    }

    public boolean doSomethingOverloading(Complex any) {
        return true;
    }

    public boolean doSomethingOverloading(Long any) {
        return true;
    }
}
