package com.todo.app.todo.controllers;

import com.todo.app.todo.dto.TodoDto;
import com.todo.app.todo.dto.TodoResponse;
import com.todo.app.todo.dto.UpdatedTodoDto;
import com.todo.app.todo.entities.Todo;
import com.todo.app.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/{userId}/todos")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final ModelMapper modelMapper;

    @GetMapping
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
    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoResponse> update(@PathVariable("todoId") Long todoId,  @RequestBody UpdatedTodoDto dto){
        dto.setId(todoId);
        System.out.println(modelMapper.map(dto, Todo.class));
        System.out.println(dto);
    return ResponseEntity.ok(todoService.update(modelMapper.map(dto, Todo.class)));
    }
    @DeleteMapping("/{todoId}")
    public HttpStatus deleteById(@PathVariable("todoId") Long todoId){
        todoService.deleteById(todoId);
        return HttpStatus.OK;
    }

}
