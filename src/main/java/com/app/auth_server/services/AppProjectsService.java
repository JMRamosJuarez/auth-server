package com.app.auth_server.services;

import com.app.auth_server.dto.CreateClientDto;
import com.app.auth_server.entities.AppProject;
import com.app.auth_server.repositories.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppProjectsService implements RegisteredClientRepository {

    private final ProjectsRepository projectsRepository;
    private final PasswordEncoder passwordEncoder;

    public AppProject create(CreateClientDto dto) {
        AppProject client = AppProject
                .builder()
                .clientId(dto.getClientId())
                .clientSecret(this.passwordEncoder.encode(dto.getClientSecret()))
                .authenticationMethods(dto.getAuthenticationMethods())
                .authorizationGrantTypes(dto.getAuthorizationGrantTypes())
                .redirectUris(dto.getRedirectUris())
                .scopes(dto.getScopes())
                .build();
        return this.projectsRepository.save(client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        return this.projectsRepository
                .findByClientId(id)
                .orElseThrow(()-> new RuntimeException("Client not found"))
                .toRegisteredClient();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.projectsRepository
                .findByClientId(clientId)
                .orElseThrow(()-> new RuntimeException("Client not found"))
                .toRegisteredClient();
    }
}
