package com.junitMockito.helper;

import org.junit.Test;

import static org.junit.Assert.*;


public class StringHelperTest {

    StringHelper helper = new StringHelper();

    @Test
    public void truncateAInFirst2Positions() {
        StringHelper helper = new StringHelper();
        assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
        assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    public void truncateAInFirst2Positions2() {
        assertEquals("CD", helper.truncateAInFirst2Positions("CAD"));
        assertEquals("CDA", helper.truncateAInFirst2Positions("CDA"));
    }

    @Test
    public void areFirstAndLastTwoCharactersTheSameTest(){
        assertTrue(helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }

    @Test
    public void areFirstAndLastTwoCharactersTheSameTest2(){
        assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABA"));
    }


}