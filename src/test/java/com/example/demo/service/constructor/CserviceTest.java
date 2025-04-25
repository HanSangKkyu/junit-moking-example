package com.example.demo.service.constructor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.service.constructor.Cservice;
import com.example.demo.service.constructor.RandomIntGenerator;

@ExtendWith(MockitoExtension.class)
class CserviceTest {

    @InjectMocks
    private Cservice cservice;

//    @Test
//    void doSomethingWithConstructor() {
//        // 클래스 내부에서 사용되는 클래스의 생성자를 mocking 하는 법
//        mockConstruction(RandomIntGenerator.class, (mock, context) -> when(mock.getNum()).thenReturn(6)); // 해제를 하지 않았기 때문에 이 파일안에서 생성자 모킹이 공유된다.
//        assertTrue(cservice.doSomethingWithConstructor());
//    }

    @Test
    void doSomethingWithConstructor2() {
        // try-with-resources 로 감싸는 것을 권장
        try (MockedConstruction<RandomIntGenerator> mocked =
                     mockConstruction(RandomIntGenerator.class, (mock, context) -> when(mock.getNum()).thenReturn(6))) {
            assertTrue(cservice.doSomethingWithConstructor()); // try-with-resources 안에서만 생성자 mocking이 동작한다.
        }
    }

    @Test
    void doSomethingWithStatic() {
        // static 메서드 mocking 하는 법
        try (MockedStatic<RandomIntGenerator> mockedStatic = Mockito.mockStatic(RandomIntGenerator.class)) {
            mockedStatic.when(() -> RandomIntGenerator.getRandomInt()).thenReturn(6);

            assertTrue(cservice.doSomethingWithStatic());
        }
    }

    @Test
    void doSomethingWithPrivateField() {
        // private field mocking 하는 법
        ReflectionTestUtils.setField(cservice, "privateField", 6);

        assertTrue(cservice.doSomethingWithPrivateField());
    }

    @Test
    void doSomethingWithPrivateMethodWithRandom() {
        // private method 를 테스트 하고 싶다면 public method를 통해서 간접적으로 테스트 해야 한다.
        try (MockedConstruction<Random> mocked =
                     mockConstruction(Random.class, (mock, context) -> when(mock.nextInt(10)).thenReturn(5))) {
            assertTrue(cservice.doSomethingWithPrivateMethodWithRandom());
        }
        try (MockedConstruction<Random> mocked =
                     mockConstruction(Random.class, (mock, context) -> when(mock.nextInt(10)).thenReturn(4))) {
            assertFalse(cservice.doSomethingWithPrivateMethodWithRandom());
        }
    }

    @Test
    void doSomethingWithPrivateMethod() throws Exception {
        // 만약 private method 를 간접적으로 테스트 하기 어렵다면 getDeclaredMethod를 통해서 테스트 할 수 있다.
        Method method = Cservice.class.getDeclaredMethod("privateMethod", int.class);
        method.setAccessible(true);

        boolean result1 = (boolean) method.invoke(cservice, 6);
        assertTrue(result1);

        boolean result2 = (boolean) method.invoke(cservice, 5);
        assertFalse(result2);
    }
}
