package com.example.demo.todolist.service;

import com.example.demo.todolist.error.MemberException;
import com.example.demo.todolist.error.MemberExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.todolist.dto.MemberDto;
import com.example.demo.todolist.repository.MemberRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByName(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_EXIST_USER));
    }

    private UserDetails createUserDetails(MemberDto memberDto) {
        return User.builder()
                .username(memberDto.getName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("DEFAULT")))
                .build();
    }
}
