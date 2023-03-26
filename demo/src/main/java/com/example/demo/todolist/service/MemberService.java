package com.example.demo.todolist.service;

import com.example.demo.todolist.dto.MemberDto;
import com.example.demo.todolist.dto.MemberRequestDto;
import com.example.demo.todolist.error.MemberException;
import com.example.demo.todolist.error.MemberExceptionType;
import com.example.demo.todolist.model.Member;
import com.example.demo.todolist.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.todolist.security.TokenProvider;

import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    // 로그인
    @Transactional
    public String login(MemberRequestDto requestDto) {
        String memberId = requestDto.getMemberId();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = requestDto.getPassword();
        MemberDto memberDto = memberRepository.findByMemberId(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_EXIST_USER));

        if(!passwordEncoder.matches(password, memberDto.getPassword())) {
            throw new MemberException(MemberExceptionType.MISMATCH_PASSWORD);
        }

        memberRepository.updateLoginCnt(memberId);

        return tokenProvider.createToken(memberDto.getName());
    }

    // 회원가입
    @Transactional
    public MemberRequestDto signup(MemberRequestDto requestDto) {
        if(!checkSignupCondition(requestDto)) {
            throw new MemberException(MemberExceptionType.INACCURATE_INFO);
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            Member member = new Member(requestDto);
            memberRepository.save(member);
            return requestDto;
        }
    }

    public boolean checkSignupCondition(MemberRequestDto requestDto) {
        String id = requestDto.getMemberId();
        String name = requestDto.getName();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        // 비밀번호는 숫자로만 구성
        String pattern = "^[0-9]*$";
        if(!Pattern.matches(pattern, password)) {
            throw new MemberException(MemberExceptionType.WRONG_FORMAT_PASSWORD);
        } else if(!password.equals(passwordCheck)) {
            throw new MemberException(MemberExceptionType.MISMATCH_PASSWORD);
        }
        // id 중복 확인
        if(memberRepository.findByMemberId(id).isPresent()) {
            throw new MemberException(MemberExceptionType.EXIST_ID);
        }
        // name은 2글자 이상
        if(name.length() <= 2) {
            throw new MemberException(MemberExceptionType.WRONG_FORMAT_NAME);
        }
        return true;
    }
}
