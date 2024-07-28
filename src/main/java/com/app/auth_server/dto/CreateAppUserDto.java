package com.app.auth_server.dto;

import com.app.auth_server.annotations.ValidRoleTypes;
import com.app.auth_server.entities.RoleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CreateAppUserDto {
    @NotBlank
    @JsonProperty("username")
    private String username;

    @Size(min = 6)
    @JsonProperty("password")
    private String password;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @Email
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("birthdate")
    private LocalDate birthdate;

    @ValidRoleTypes
    @JsonProperty("roles")
    private List<RoleType> roles;
}
