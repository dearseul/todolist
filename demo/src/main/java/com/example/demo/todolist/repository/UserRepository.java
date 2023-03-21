package com.example.demo.todolist.repository;

import com.example.demo.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.todolist.dto.UserDto;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDto> findByUserId(String userId);
    Optional<UserDto> findByName(String username);
}
