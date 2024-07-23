package com.app.auth_server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_clients")
public class AppClient {

    @Id
    @JsonProperty("client_id")
    @Column(name = "client_id")
    private String clientId;

    @JsonProperty("client_secret")
    @Column(name = "client_secret")
    private String clientSecret;

    @JsonProperty("client_authentication_methods")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;

    @JsonProperty("authorization_grant_types")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<AuthorizationGrantType> authorizationGrantTypes;

    @JsonProperty("redirect_uris")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> redirectUris;

    @JsonProperty("scopes")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scopes;

    public RegisteredClient toRegisteredClient() {
        return RegisteredClient
                .withId(this.clientId)
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(this.clientAuthenticationMethods))
                .authorizationGrantTypes(gt -> gt.addAll(this.authorizationGrantTypes))
                .redirectUris(ru -> ru.addAll(this.redirectUris))
                .scopes(sc -> sc.addAll(this.scopes))
                .clientSettings(
                        ClientSettings.builder().requireAuthorizationConsent(true).build()
                )
                .build();
    }
}
