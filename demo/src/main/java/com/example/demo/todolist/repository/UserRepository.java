package com.example.demo.todolist.repository;

import com.example.demo.todolist.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.todolist.dto.UsersDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<UsersDto> findByUserId(String userId);
}
