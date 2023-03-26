package com.example.demo.todolist.model;

import com.example.demo.todolist.dto.MemberRequestDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(name = "member_id", unique = true)
    private String memberId;

    private String password;

    private String name;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createDt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginDt = LocalDateTime.now();

    private String imgUrl;

    @Column(name = "login_count")
    private int loginCnt;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    protected Member() {
    }

    // TODO builder 처리
    public Member(MemberRequestDto requestDto) {
        this.memberId = requestDto.getMemberId();
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.createDt = LocalDateTime.now();
    }
}
