package com.junitMockito.helper;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;

public class ArraysCompareTest {

    @Test
    public void test(){
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};

        Arrays.sort(numbers);

        assertArrayEquals(expected, numbers);
    }

    @Test(expected = NullPointerException.class)
    public void testWithExceprion(){
        int[] numbers = null;

        Arrays.sort(numbers);
    }

    @Test(timeout = 100)
    public void testSort_Performance(){
        int[] numbers = {12, 3, 4, 1};

        for (int i = 0; i < 1_000_000 ; i++) {
            numbers[0] = i;
            Arrays.sort(numbers);
        }

    }
}
