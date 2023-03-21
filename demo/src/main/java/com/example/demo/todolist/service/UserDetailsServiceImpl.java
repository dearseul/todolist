package com.example.demo.todolist.service;

import com.example.demo.todolist.error.UserException;
import com.example.demo.todolist.error.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.todolist.dto.UserDto;
import com.example.demo.todolist.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_EXIST_USER));
    }

    private UserDetails createUserDetails(UserDto userDto) {
        return User.builder()
                .username(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("DEFAULT")))
                .build();
    }
}
