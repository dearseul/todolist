package com.example.demo.todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class List {
    @Id @GeneratedValue
    private long seq;
    private long userSeq;
    private String title;
    private char isDone;
    private String scope;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

}
