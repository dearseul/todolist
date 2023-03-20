package com.example.demo.todolist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity // todo Builder
public class Users {
    @Id @GeneratedValue
    private Long seq;

    @Column(name = "user_id")
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

}
