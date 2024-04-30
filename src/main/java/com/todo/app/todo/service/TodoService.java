package com.todo.app.todo.service;

import com.todo.app.todo.entities.Todo;

import java.util.List;

public interface TodoService {
    public List<Todo> findAllByUserId(Long id);
    public Todo updateStatus(Todo todo);
    public Todo updateTitle(Todo todo);
    public void deleteById(Long id);
}
