package com.app.auth_server.services;

import com.app.auth_server.dto.SignUpDto;
import com.app.auth_server.entities.AppUser;
import com.app.auth_server.entities.Role;
import com.app.auth_server.entities.RoleType;
import com.app.auth_server.repositories.AppUsersRepository;
import com.app.auth_server.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUsersRepository appUsersRepository;
    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    public AppUser signUp(SignUpDto request) {
        Set<Role> roles = request.getRoles()
                .stream()
                .map(r -> this.rolesRepository.findByType(RoleType.valueOf(r)).orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());

        final AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .birthDay(request.getBirthDay())
                .roles(roles)
                .build();

        return this.appUsersRepository.save(user);
    }
}
