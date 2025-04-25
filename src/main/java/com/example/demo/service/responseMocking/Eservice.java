package com.example.demo.service.responseMocking;

import org.springframework.stereotype.Service;

@Service
public class Eservice {
    public Einterface getInterface(){
        return new EinterfaceImpl();
    }
}
