package com.example.demo.todolist.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListDto {
    private long seq;
    private long userSeq;
    private String title;
    private char isDone;
    private String scope;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;

    public ListDto(long seq, long userSeq, String title, char isDone, String scope, LocalDateTime createDt, LocalDateTime updateDt) {
        this.seq = seq;
        this.userSeq = userSeq;
        this.title = title;
        this.isDone = isDone;
        this.scope = scope;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
