package com.app.auth_server.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AppErrorDto(String message, List<AppFieldErrorDto> errors) {
}
