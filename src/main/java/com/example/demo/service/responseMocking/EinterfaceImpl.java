package com.example.demo.service.responseMocking;

import java.util.Random;

public class EinterfaceImpl implements Einterface {

    @Override
    public int getMethod1() {
        return new Random().nextInt(10) + 1;
    }

    @Override
    public int getMethod2() {
        return new Random().nextInt(10) + 1;
    }

    @Override
    public int getMethod3() {
        return new Random().nextInt(10) + 1;
    }

    @Override
    public int getMethod4() {
        return new Random().nextInt(10) + 1;
    }
}
