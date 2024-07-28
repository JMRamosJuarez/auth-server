package com.app.auth_server.controllers;

import com.app.auth_server.dto.AppUserDto;
import com.app.auth_server.dto.CreateAppUserDto;
import com.app.auth_server.services.AppUsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppUsersController {

    @Autowired
    private AppUsersService usersService;

    @PostMapping("/sign-up")
    public ResponseEntity<AppUserDto> signUp(@Valid @RequestBody CreateAppUserDto createAppUserDto) {
        final AppUserDto userDto = this.usersService.signUp(createAppUserDto);
        return ResponseEntity.ok(userDto);
    }
}
