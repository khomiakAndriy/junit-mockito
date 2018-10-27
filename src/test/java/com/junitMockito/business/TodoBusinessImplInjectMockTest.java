package com.junitMockito.business;

import com.junitMockito.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplInjectMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusiness;
    @Test
    public void todoServiceWithMockTest() {

        List<String> todosFromMock = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos(anyString())).thenReturn(todosFromMock);

        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("Andriy");

        assertEquals(2, todos.size());
    }

    @Test
    public void todoServiceWithUsingBDDTest() {
        // Given
        List<String> todosFromMock = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(todoServiceMock.retrieveTodos(anyString())).willReturn(todosFromMock);

        //When
        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("Andriy");

        //Then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void letsTestDeleteNow() {

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusiness.deleteTodosNotRelatedToSpring("Ranga");

        verify(todoServiceMock).deleteTodo("Learn to Dance");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring");

        then(todoServiceMock).should().deleteTodo("Learn to Dance");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");

    }

    @Test
    public void letsTestDelete_withArgCapture() {

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusiness.deleteTodosNotRelatedToSpring("Ranga");

        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());

    }
}