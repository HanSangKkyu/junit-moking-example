package com.example.demo.service.anyExample;

import org.springframework.stereotype.Service;

import com.example.demo.dto.Complex;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Aservice {
    private final Bservice bservice;

    public boolean doSomething() {
        // 복잡한 객체 설정
        Complex complex = new Complex();
        complex.setField0("field0");

        boolean result = bservice.doSomething(complex);

        if (result) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingOverloading(){
        Complex complex = new Complex();
        complex.setField0("field0");

        boolean result = bservice.doSomethingOverloading(complex);
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingArgTwo() {
        Complex complex = new Complex();
        complex.setField0("field0");
        boolean result = bservice.doSomethingArgTwo(complex, 1);
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doSomethingOverloadingArgNull() {
        Complex complex = null;
        boolean result = bservice.doSomethingOverloading(complex);
        if (result) {
            return true;
        } else {
            return false;
        }
    }
}
