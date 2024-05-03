package com.todo.app.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на обновление пользователя")
public class UpdateProfileDto {
    @Schema(description = "имя", example = "John Doe")
    @Size(min = 2, max = 25, message = "Имя должно содержать от 2 до 25 символов")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;
}
