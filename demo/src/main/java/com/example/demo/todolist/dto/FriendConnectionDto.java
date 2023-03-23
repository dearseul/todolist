package com.example.demo.todolist.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendConnectionDto {
    private long seq;
    private String writerId;
    private long friendSeq;
    private LocalDateTime createDt;

    public FriendConnectionDto(long seq, String writerId, long friendSeq, LocalDateTime createDt) {
        this.seq = seq;
        this.writerId = writerId;
        this.friendSeq = friendSeq;
        this.createDt = createDt;
    }
}
