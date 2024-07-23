package com.app.auth_server.services;

import com.app.auth_server.entities.AppClient;
import com.app.auth_server.repositories.AppClientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisteredAppClientsRepository implements RegisteredClientRepository {

    private final AppClientsRepository appClientsRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        AppClient client = AppClient
                .builder()
                .id(registeredClient.getId())
                .clientId(registeredClient.getClientId())
                .clientSecret(registeredClient.getClientSecret())
                .clientName(registeredClient.getClientName())
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
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"))
                .toRegisteredClient();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.appClientsRepository
                .findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"))
                .toRegisteredClient();
    }
}
