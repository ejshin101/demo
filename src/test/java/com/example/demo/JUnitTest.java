package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("덧셈") // 테스트 명
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 4;

        // a + b를 더한값과 sum 값이 같은지 확인
        Assertions.assertEquals(sum, a + b);
    }
}
