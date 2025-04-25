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

        // 내부 함수를 모두 구현해야 하기 때문에 너무 코드가 길어진다.
//        when(eservice.getInterface()).thenReturn(new Einterface() {
//            @Override
//            public int getMethod1() {
//                return 6;
//            }
//
//            @Override
//            public int getMethod2() {
//                return 0;
//            }
//
//            @Override
//            public int getMethod3() {
//                return 0;
//            }
//
//            @Override
//            public int getMethod4() {
//                return 0;
//            }
//        });

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
