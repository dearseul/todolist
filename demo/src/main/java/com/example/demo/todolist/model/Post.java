package com.example.demo.todolist.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id @GeneratedValue
    private long seq;
    private long userSeq;
    private String title;
    private char isDone;
    private String scope;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
