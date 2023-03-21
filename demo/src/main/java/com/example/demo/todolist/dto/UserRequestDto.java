package com.example.demo.todolist.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String userId;
    private String name;
    private String password;
    private String passwordCheck;
}
