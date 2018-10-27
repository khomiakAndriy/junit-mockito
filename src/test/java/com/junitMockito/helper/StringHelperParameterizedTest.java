package com.junitMockito.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

    StringHelper helper = new StringHelper();

    private String input;
    private String expected;

    public StringHelperParameterizedTest(String input, String axpected) {
        this.input = input;
        this.expected = axpected;
    }

    @Parameterized.Parameters
    public static Collection<String[]> testConditions(){
        String[][] expectedOutputs= {
            {"AACD", "CD"},
            {"ACD", "CD"}};
        return Arrays.asList(expectedOutputs);
    }

    @Test
    public void truncateAInFirst2Positions() {

        assertEquals(expected, helper.truncateAInFirst2Positions(input));
    }


}