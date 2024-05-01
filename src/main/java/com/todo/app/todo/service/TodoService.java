package com.todo.app.todo.service;

import com.todo.app.todo.dto.TodoDto;
import com.todo.app.todo.dto.TodoResponse;
import com.todo.app.todo.entities.Todo;

import java.util.List;

public interface TodoService {
    public List<TodoResponse> findAllByUserId(Long id);
    public Todo update(Todo todo);
    public TodoResponse save(TodoDto todo);
    public void deleteById(Long id);
}
