package com.example.demo.todolist.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsersDto {
    private Long seq;
    private String userId;
    private String password;
    private String name;
    private LocalDateTime createDt;
    private LocalDateTime lastLoginDt;
    private String imgUrl;
    private int loginCnt;

    public UsersDto(Long seq, String userId, String password, String name, LocalDateTime createDt, LocalDateTime lastLoginDt, String imgUrl, int loginCnt) {
        this.seq = seq;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.createDt = createDt;
        this.lastLoginDt = lastLoginDt;
        this.imgUrl = imgUrl;
        this.loginCnt = loginCnt;
    }


}
