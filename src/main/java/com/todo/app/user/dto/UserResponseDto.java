package com.todo.app.user.dto;

import com.todo.app.todo.entities.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ c данными пользователя")
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<Todo> todos;
}
