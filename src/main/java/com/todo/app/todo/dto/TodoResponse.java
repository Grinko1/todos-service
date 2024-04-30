package com.todo.app.todo.dto;

import com.todo.app.todo.entities.TodoStatus;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TodoResponse {
    private Long id;
    private String title;
    private TodoStatus status;
}
