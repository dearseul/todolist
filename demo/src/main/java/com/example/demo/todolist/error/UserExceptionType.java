package com.example.demo.todolist.error;

import org.springframework.http.HttpStatus;

public enum UserExceptionType implements BaseExceptionType{
    // TODO 에러 출력 미흡
    // 회원가입
    EXIST_ID(700, HttpStatus.OK, "이미 존재하는 아이디입니다."),
    WRONG_FORMAT_PASSWORD(701, HttpStatus.OK, "잘못된 비밀번호 형식입니다.(숫자로 구성되어야 합니다.)"),
    MISMATCH_PASSWORD(702, HttpStatus.OK, "비밀번호를 다시 확인해주세요."),
    INACCURATE_INFO(703, HttpStatus.OK, "회원가입 정보가 정확하지 않습니다."),
    WRONG_FORMAT_NAME(704, HttpStatus.OK, "이름은 두 글자 이상이어야 합니다."),
    // 로그인
    NOT_EXIST_USER(705, HttpStatus.OK, "존재하지 않는 아이디입니다.");


    UserExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
    }

    @Override
    public int getErrorCode() {
        return 0;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }
}
