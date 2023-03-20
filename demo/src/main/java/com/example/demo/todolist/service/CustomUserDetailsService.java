package com.example.demo.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.todolist.dto.UsersDto;
import com.example.demo.todolist.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 X"));
    }

    private UserDetails createUserDetails(UsersDto usersDto) {
        return User.builder()
                .username(usersDto.getName())
                .password(passwordEncoder.encode(usersDto.getPassword()))
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("DEFAULT")))
                .build();
    }
}
