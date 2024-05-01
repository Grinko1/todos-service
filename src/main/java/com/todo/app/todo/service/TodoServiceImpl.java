package com.todo.app.todo.service;

import com.todo.app.exceptions.NotFoundException;
import com.todo.app.todo.dto.TodoDto;
import com.todo.app.todo.dto.TodoResponse;
import com.todo.app.todo.entities.Todo;
import com.todo.app.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public List<TodoResponse> findAllByUserId(Long id) {
        return todoRepository.findAllByUserId(id).stream().map(todo -> modelMapper.map(todo, TodoResponse.class)).collect(Collectors.toList());
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

    public TodoResponse save(TodoDto todo) {
        Todo newTodo = modelMapper.map(todo, Todo.class);
        newTodo.setId(null);
        System.out.println("new todo : " + newTodo);
        return modelMapper.map(todoRepository.save(newTodo), TodoResponse.class);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

}
