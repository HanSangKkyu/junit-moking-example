package com.example.demo.service.anyExample;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.Complex;

@ExtendWith(MockitoExtension.class)
class AserviceTest {

    @InjectMocks
    private Aservice aservice;

    @Mock
    private Bservice bservice;

    @Test
    void doSomething() {
        // any()를 사용하면 복잡한 객체에 대해 test fixture를 만들지 않아도 된다.
        when(bservice.doSomething(any())).thenReturn(true);

        assertTrue(aservice.doSomething());
    }

    @Test
    void doSomething_with_type() {
        // 오버로딩이 가능한 메서드에 any()를 사용하고 싶다면 타입을 명시해주어야 한다
//        when(bservice.doSomethingOverloading(any())).thenReturn(true);
        when(bservice.doSomethingOverloading(any(Complex.class))).thenReturn(true);

        assertTrue(aservice.doSomethingOverloading());
    }

    @Test
    void doSomethingArgTwo() {
        // 주의: any()를 사용했다면 나머지 인자에는 raw value를 사용할 수 없다.
//         when(bservice.doSomethingArgTwo(any(Complex.class), 1)).thenReturn(true); // 에러 발생
        when(bservice.doSomethingArgTwo(any(Complex.class), eq(1))).thenReturn(true);

        assertTrue(aservice.doSomethingArgTwo());
    }

    @Test
    void doSomethingOverloadingArgNull() {
        // 오버로딩이 가능한 메서드에 any()를 사용할 수 없음
//        when(bservice.doSomethingOverloading(any())).thenReturn(true); // 에러 발생

        // 타입을 명시해주었지만 null이 입력되기 때문에 mismatch가 발생한다.
//        when(bservice.doSomethingOverloading(any(Complex.class))).thenReturn(true); // 에러 발생
        when(bservice.doSomethingOverloading(isNull(Complex.class))).thenReturn(true);

        assertTrue(aservice.doSomethingOverloadingArgNull());
    }
}
