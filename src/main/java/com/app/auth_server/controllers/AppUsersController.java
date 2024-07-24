package com.app.auth_server.controllers;

import com.app.auth_server.dto.AppUserDto;
import com.app.auth_server.dto.CreateAppUserDto;
import com.app.auth_server.services.AppUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class AppUsersController {

    @Autowired
    private AppUsersService usersService;

    @PostMapping
    public ResponseEntity<AppUserDto> create(@RequestBody CreateAppUserDto createAppUserDto) {
        final AppUserDto userDto = this.usersService.create(createAppUserDto);
        return ResponseEntity.ok(userDto);
    }
}
