package com.example.demo.todolist.service;

import com.example.demo.todolist.dto.UserDto;
import com.example.demo.todolist.dto.UserRequestDto;
import com.example.demo.todolist.error.UserException;
import com.example.demo.todolist.error.UserExceptionType;
import com.example.demo.todolist.model.User;
import com.example.demo.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.todolist.security.TokenProvider;

import java.util.Collections;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    // 로그인
    @Transactional
    public String login(UserRequestDto requestDto) {
        // TODO 로그인시 로그이날짜 update
        String userId = requestDto.getUserId();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = requestDto.getPassword();
        UserDto userDto = userRepository.findByUserId(userId).orElseThrow(() -> new UserException(UserExceptionType.NOT_EXIST_USER));

        if(!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new UserException(UserExceptionType.MISMATCH_PASSWORD);
        }

        return tokenProvider.createToken(userDto.getName());
    }

    // 회원가입
    @Transactional
    public UserRequestDto signup(UserRequestDto requestDto) {
        if(!checkSignupCondition(requestDto)) {
            throw new UserException(UserExceptionType.INACCURATE_INFO);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            User user = new User(requestDto);
            userRepository.save(user);
            return requestDto;
        }
    }

    public boolean checkSignupCondition(UserRequestDto requestDto) {
        String id = requestDto.getUserId();
        String name = requestDto.getName();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        // 비밀번호는 숫자로만 구성
        String pattern = "^[0-9]*$";
        if(!Pattern.matches(pattern, password)) {
            throw new UserException(UserExceptionType.WRONG_FORMAT_PASSWORD);
        } else if(!password.equals(passwordCheck)) {
            throw new UserException(UserExceptionType.MISMATCH_PASSWORD);
        }
        // id 중복 확인
        if(userRepository.findByUserId(id).isPresent()) {
            throw new UserException(UserExceptionType.EXIST_ID);
        }
        // name은 2글자 이상
        if(name.length() <= 2) {
            throw new UserException(UserExceptionType.WRONG_FORMAT_NAME);
        }
        return true;
    }
}
