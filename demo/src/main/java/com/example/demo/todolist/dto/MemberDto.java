package com.example.demo.todolist.dto;

import lombok.Data;

@Data
public class MemberDto {
//    private Long seq;
    private String memberId;
    private String password;
    private String name;
//    private LocalDateTime createDt;
//    private LocalDateTime lastLoginDt;
//    private String imgUrl;
//    private int loginCnt;
//    private List<Post> posts = new ArrayList<>();


    public MemberDto(String memberId, String password, String name) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
    }
}
