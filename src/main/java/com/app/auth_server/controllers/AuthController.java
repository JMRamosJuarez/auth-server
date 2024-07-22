package com.app.auth_server.controllers;

import com.app.auth_server.dto.SignUpDto;
import com.app.auth_server.entities.AppUser;
import com.app.auth_server.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<AppUser> signUp(
            @RequestBody SignUpDto request
    ) {
        final AppUser token = this.authService.signUp(request);
        return ResponseEntity.ok(token);
    }
}
