package com.junitMockito.business;

import com.junitMockito.data.api.TodoService;
import com.junitMockito.data.api.TodoServiceStub;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TodoBusinessImplStubTest {

    @Test
    public void todoSetviceWithStubTest() {
        TodoService todoServiceStub = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceStub);

        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("Andriy");

        assertEquals(2, todos.size());
    }
}