package com.example.demo.todolist.dto;

import lombok.Data;

@Data
public class MemberRequestDto {
    private String memberId;
    private String name;
    private String password;
    private String passwordCheck;
}
