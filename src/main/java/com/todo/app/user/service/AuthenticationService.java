package com.todo.app.user.service;

import com.todo.app.config.jwt.JwtService;
import com.todo.app.user.dto.*;
import com.todo.app.user.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponse signUp(SignUpDto request) {
        System.out.println(request);
        try{
            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            User userData = userService.create(user);

            var jwt = jwtService.generateToken(user);
            return new JwtAuthResponse(jwt, modelMapper.map(userData, UserInfoDto.class) );
        }catch (RuntimeException e){
            throw e;
        }

    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthResponse signIn(SignInDto request) {
        System.out.println("sign in");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Wrong email or password");
        }


        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        User userData = userService.getByEmail(request.getEmail());

        System.out.println("profile " + userData);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt, modelMapper.map(userData, UserInfoDto.class) );
    }

}
