package com.todo.app.todo.service;

import com.todo.app.todo.entities.Todo;

import java.util.List;

public interface TodoService {
    public List<Todo> findAllByUserId(Long id);
    public Todo update(Todo todo);
    public Todo save(Todo todo);
    public void deleteById(Long id);
}
