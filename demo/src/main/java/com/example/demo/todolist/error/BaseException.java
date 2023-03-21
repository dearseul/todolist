package com.example.demo.todolist.error;

public abstract class BaseException extends RuntimeException {
    public abstract BaseExceptionType getExceptionType();
}
