package com.todo.app.user.service;

import com.todo.app.config.jwt.JwtService;
import com.todo.app.exceptions.NotFoundException;
import com.todo.app.user.dto.JwtAuthResponse;
import com.todo.app.user.dto.UpdateProfileDto;
import com.todo.app.user.dto.UserInfoDto;
import com.todo.app.user.dto.UserResponseDto;
import com.todo.app.user.entity.User;
import com.todo.app.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    private User save (User user){
        return userRepository.save(user);
    }
    public User create (User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }
    public JwtAuthResponse updateProfile(UpdateProfileDto data, Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User existingUser = userOptional.get();
            if(!existingUser.getName().equals(data.getName())){
                existingUser.setName(data.getName());
            }
            if(!existingUser.getEmail().equals(data.getEmail())){
                existingUser.setEmail(data.getEmail());
            }
            User user = userRepository.save(existingUser);
            var jwt = jwtService.generateToken(user);
            return new JwtAuthResponse(jwt, modelMapper.map(user, UserInfoDto.class)) ;

        }else{
            throw new NotFoundException("User", "Id", id);
        }

    }
    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(email);
    }


}
