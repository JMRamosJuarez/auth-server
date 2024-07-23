package com.app.auth_server.controllers;

import com.app.auth_server.dto.CreateClientDto;
import com.app.auth_server.entities.AppClient;
import com.app.auth_server.services.AppClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final AppClientsService appClientsService;

    @PostMapping("/")
    public ResponseEntity<AppClient> create(
            @RequestBody CreateClientDto request
    ) {
        final AppClient client = this.appClientsService.create(request);
        return ResponseEntity.ok(client);
    }
}
