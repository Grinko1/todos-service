package com.todo.app.todo.controllers;

import com.todo.app.todo.entities.Todo;
import com.todo.app.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/{userId}")
    public ResponseEntity<List<Todo>> getAllTodos(@PathVariable("userId") Long userId){
        return  ResponseEntity.ok(todoService.findAllByUserId(userId));
    }
}
