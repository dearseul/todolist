package com.example.demo.todolist.repository;

import com.example.demo.todolist.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.todolist.dto.MemberDto;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select new com.example.demo.todolist.dto.MemberDto(m.memberId, m.password, m.name) from Member m where m.memberId=:memberId")
    Optional<MemberDto> findByMemberId(@Param("memberId") String memberId);

    @Query("select new com.example.demo.todolist.dto.MemberDto(m.memberId, m.password, m.name) from Member m where m.name=:name")
    Optional<MemberDto> findByName(@Param("name") String name);

    @Modifying
    @Query("update Member m set m.login_count=m.login_count+1 where m.user_id=:userId")
    void updateLoginCnt(@Param("userId") String userId);

}
