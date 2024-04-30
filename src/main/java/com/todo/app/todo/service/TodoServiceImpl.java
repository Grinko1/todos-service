package com.todo.app.todo.service;

import com.todo.app.todo.entities.Todo;
import com.todo.app.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public List<Todo> findAllByUserId(Long id){
        return todoRepository.findAllByUserId(id);
    }

    public Todo updateStatus(Todo todo){
        return todoRepository.save(todo);
    }
    public Todo updateTitle(Todo todo){
        return todoRepository.save(todo);
    }
    public void deleteById(Long id){
        todoRepository.deleteById(id);
    }

}
