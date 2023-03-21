package com.example.demo.todolist.model;

import com.example.demo.todolist.dto.UserRequestDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(name = "user_id", unique = true)
    private String userId;

    private String password;

    private String name;

    @Column(name = "create_at")
    private LocalDateTime createDt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginDt;

    private String imgUrl;

    @Column(name = "login_count")
    private int loginCnt;

    public User() {
    }

    // TODO builder 처리
    public User(UserRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.createDt = LocalDateTime.now();
    }
}
