package com.junitMockito.helper;

import org.junit.*;

public class QuickBeforeAfterTest {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("Before class");
    }

    @Before
    public void setup(){
        System.out.println("Before test");
    }

    @Test
    public void test1(){
        System.out.println("Test 1");
    }

    @Test
    public void test2(){
        System.out.println("Test 2");
    }

    @After
    public void tearDown(){
        System.out.println("After test");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("After class");
    }
}
