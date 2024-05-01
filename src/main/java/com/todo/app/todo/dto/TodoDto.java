package com.todo.app.todo.dto;

import com.todo.app.todo.entities.TodoStatus;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TodoDto {
    private String title;
    private TodoStatus status;
    private Long userId;
}
