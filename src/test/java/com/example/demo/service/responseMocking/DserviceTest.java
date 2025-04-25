package com.example.demo.service.responseMocking;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DserviceTest {

    @InjectMocks
    private Dservice dservice;
    @Mock
    private Eservice eservice;

    @Test
    void doSomething() {
        // interface를 return하는 메서드 mocking 하는 법
//        when(eservice.getInterface().getMethod1()).thenReturn(6); // NPE 발생 eservice.getInterface() 에 대해 먼저 mocking을 해주어야 한다.

        Einterface einterface = mock(Einterface.class);
        when(einterface.getMethod1()).thenReturn(6);
        when(eservice.getInterface()).thenReturn(einterface);

        assertTrue(dservice.doSomething());
    }

    @Test
    void doSomethingTwice() {
        // 같은 메서드를 두번 호출하는데 다른 값을 리턴하게 mocking 하는 법
        Einterface einterface = mock(Einterface.class);

        // 잘못된 예시 마지막 것만 적용된다.
//        when(einterface.getMethod1()).thenReturn(6);
//        when(einterface.getMethod1()).thenReturn(5);

        // 두번 호출되기 때문에 두번째 호출에 대한 값을 설정해주어야 한다.
        when(einterface.getMethod1()).thenReturn(6).thenReturn(5);

        when(eservice.getInterface()).thenReturn(einterface);

        assertTrue(dservice.doSomethingTwice());
    }
}
