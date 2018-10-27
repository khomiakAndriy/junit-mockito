package com.junitMockito.business;

import com.junitMockito.data.api.TodoService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ListTest {

    @Test
    public void listSizeTest() {
        List listmock = mock(List.class);
        when(listmock.size()).thenReturn(2);

        assertEquals(2, listmock.size());
    }

    @Test
    public void listSizeMultipleReturnTest() {
        List listmock = mock(List.class);
        when(listmock.size()).thenReturn(2).thenReturn(3);

        assertEquals(2, listmock.size());
        assertEquals(3, listmock.size());
    }

    @Test
    public void listGetTest() {
        List listmock = mock(List.class);
        when(listmock.get(anyInt())).thenReturn("return");

        assertEquals("return", listmock.get(0));
        assertEquals("return", listmock.get(1));
    }

    @Test(expected = RuntimeException.class)
    public void listGetThrowExceptionTest() {
        List listmock = mock(List.class);
        when(listmock.get(anyInt())).thenThrow(new RuntimeException("Test"));

        listmock.get(0);

    }
}