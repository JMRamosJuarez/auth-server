package com.app.auth_server.services;

import com.app.auth_server.dto.CreateClientDto;
import com.app.auth_server.entities.AppClient;
import com.app.auth_server.repositories.AppClientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppClientsService implements RegisteredClientRepository {

    private final AppClientsRepository appClientsRepository;
    private final PasswordEncoder passwordEncoder;

    public AppClient create(CreateClientDto dto) {
        AppClient client = AppClient
                .builder()
                .clientId(dto.getClientId())
                .clientSecret(this.passwordEncoder.encode(dto.getClientSecret()))
                .clientAuthenticationMethods(dto.getClientAuthenticationMethods())
                .authorizationGrantTypes(dto.getAuthorizationGrantTypes())
                .redirectUris(dto.getRedirectUris())
                .scopes(dto.getScopes())
                .build();
        return this.appClientsRepository.save(client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        AppClient client = AppClient
                .builder()
                .clientId(registeredClient.getClientId())
                .clientSecret(this.passwordEncoder.encode(registeredClient.getClientSecret()))
                .clientAuthenticationMethods(registeredClient.getClientAuthenticationMethods())
                .authorizationGrantTypes(registeredClient.getAuthorizationGrantTypes())
                .redirectUris(registeredClient.getRedirectUris())
                .scopes(registeredClient.getScopes())
                .build();
        this.appClientsRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        return this.appClientsRepository
                .findByClientId(id)
                .orElseThrow(()-> new RuntimeException("Client not found"))
                .toRegisteredClient();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.appClientsRepository
                .findByClientId(clientId)
                .orElseThrow(()-> new RuntimeException("Client not found"))
                .toRegisteredClient();
    }
}
