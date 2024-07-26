package com.app.auth_server.services;

import com.app.auth_server.entities.OAuthClient;
import com.app.auth_server.repositories.OAuthClientsRepository;
import com.app.auth_server.settings.JwtSettings;
import com.app.auth_server.settings.OAuthClientSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthClientsService implements RegisteredClientRepository {

    private final JwtSettings jwtSettings;

    private final OAuthClientSettings oAuthClientSettings;

    private final OAuthClientsRepository oAuthClientsRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        final OAuthClient client = OAuthClient
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

        this.oAuthClientsRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        return this.oAuthClientsRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"))
                .toRegisteredClient(
                        this.oAuthClientSettings.getClientSettings(),
                        this.jwtSettings.getTokenSettings()
                );
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.oAuthClientsRepository
                .findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"))
                .toRegisteredClient(
                        this.oAuthClientSettings.getClientSettings(),
                        this.jwtSettings.getTokenSettings()
                );
    }
}
