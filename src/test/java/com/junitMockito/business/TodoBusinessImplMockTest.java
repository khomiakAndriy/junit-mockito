package com.junitMockito.business;

import com.junitMockito.data.api.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class TodoBusinessImplMockTest {

    @Test
    public void todoServiceWithMockTest() {

        List<String> todosFromMock = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        TodoService todoServiceMock = mock(TodoService.class);

        when(todoServiceMock.retrieveTodos(anyString())).thenReturn(todosFromMock);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("Andriy");

        assertEquals(2, todos.size());
    }

    @Test
    public void todoServiceWithUsingBDDTest() {
        // Given
        List<String> todosFromMock = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        TodoService todoServiceMock = mock(TodoService.class);

        given(todoServiceMock.retrieveTodos(anyString())).willReturn(todosFromMock);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        //When
        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("Andriy");

        //Then
        assertThat(todos.size(), is(2));
    }

    @Test
    public void letsTestDeleteNow() {

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        verify(todoService).deleteTodo("Learn to Dance");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");

        then(todoService).should().deleteTodo("Learn to Dance");
        then(todoService).should(never()).deleteTodo("Learn Spring MVC");
        then(todoService).should(never()).deleteTodo("Learn Spring");

        verify(todoService, times(1)).deleteTodo("Learn to Dance");

    }

    @Test
    public void letsTestDelete_withArgCapture() {

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        then(todoService).should().deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());

    }
}