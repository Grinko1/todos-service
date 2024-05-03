package com.todo.app.user.controller;

import com.todo.app.exceptions.AppError;
import com.todo.app.user.dto.JwtAuthResponse;
import com.todo.app.user.dto.SignInDto;
import com.todo.app.user.dto.SignUpDto;
import com.todo.app.user.dto.UpdateProfileDto;
import com.todo.app.user.service.AuthenticationService;
import com.todo.app.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto request) {
        try{
            return  ResponseEntity.ok(authenticationService.signUp(request));
        }catch (RuntimeException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User with this email already exist"), HttpStatus.BAD_REQUEST);
        }

    }


    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto request) {
        try {
            JwtAuthResponse response = authenticationService.signIn(request);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Wrong email or password"), HttpStatus.UNAUTHORIZED);
        }
    }
    @PatchMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateProfileDto dto, @PathVariable("userId") Long userId){
        System.out.println(dto);
        try{
            return ResponseEntity.ok(userService.updateProfile(dto, userId));
        }catch (RuntimeException e){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Failed update profile data"), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        System.out.println("refresh");
//        todo refresh
//        service.refreshToken(request, response);
    }
}
