package com.todo.app.todo.service;

import com.todo.app.exceptions.NotFoundException;
import com.todo.app.todo.entities.Todo;
import com.todo.app.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> findAllByUserId(Long id) {
        return todoRepository.findAllByUserId(id);
    }

    public Todo update(Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(updatedTodo.getId());
        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();


            if (!existingTodo.getTitle().equals(updatedTodo.getTitle())) {
                existingTodo.setTitle(updatedTodo.getTitle());
            }
            if (existingTodo.getStatus() != updatedTodo.getStatus()) {
                existingTodo.setStatus(updatedTodo.getStatus());
            }

            return todoRepository.save(existingTodo);
        } else {
            throw new NotFoundException("Todo", "id", updatedTodo.getId());
        }
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

}
