package com.app.auth_server.controllers;

import com.app.auth_server.dto.AppErrorDto;
import com.app.auth_server.dto.AppFieldErrorDto;
import com.app.auth_server.errors.ClientNotFoundException;
import com.app.auth_server.errors.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AppErrorDto handle(MethodArgumentNotValidException exception) {
        return AppErrorDto.builder()
                .message(exception.getMessage())
                .errors(
                        exception
                                .getFieldErrors()
                                .stream()
                                .map(
                                        error -> new AppFieldErrorDto(error.getField(), error.getDefaultMessage())
                                )
                                .toList()
                ).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public AppErrorDto handle(RoleNotFoundException exception) {
        return AppErrorDto.builder()
                .message(exception.getMessage())
                .errors(List.of(
                        new AppFieldErrorDto("role", exception.getLocalizedMessage())
                )).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientNotFoundException.class)
    public AppErrorDto handle(ClientNotFoundException exception) {
        return AppErrorDto.builder()
                .message(exception.getMessage())
                .errors(List.of(
                        new AppFieldErrorDto("client_id", exception.getLocalizedMessage())
                )).build();
    }
}
