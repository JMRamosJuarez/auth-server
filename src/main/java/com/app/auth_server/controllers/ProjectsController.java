package com.app.auth_server.controllers;

import com.app.auth_server.dto.CreateClientDto;
import com.app.auth_server.entities.AppProject;
import com.app.auth_server.services.AppProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final AppProjectsService appProjectsService;

    @PostMapping("/")
    public ResponseEntity<AppProject> create(
            @RequestBody CreateClientDto request
    ) {
        final AppProject client = this.appProjectsService.create(request);
        return ResponseEntity.ok(client);
    }
}
