package com.todo.app.todo.controllers;

import com.todo.app.todo.dto.TodoDto;
import com.todo.app.todo.dto.TodoResponse;
import com.todo.app.todo.entities.Todo;
import com.todo.app.todo.service.TodoService;
import com.todo.app.user.entity.User;
import com.todo.app.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<List<TodoResponse>> getAllTodos(@PathVariable("userId") Long userId){
        return  ResponseEntity.ok(todoService.findAllByUserId(userId));
    }
    @PostMapping
    public ResponseEntity<TodoResponse> save(@RequestBody TodoDto todo){
        System.out.println(todo);

        TodoResponse response = todoService.save(todo);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

}
