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
@Table(name = "oauth_clients")
public class OAuthClient {

    @Id
    private String id;

    @JsonProperty("client_id")
    @Column(name = "client_id")
    private String clientId;

    @JsonProperty("client_secret")
    @Column(name = "client_secret")
    private String clientSecret;

    @JsonProperty("client_name")
    @Column(name = "client_name")
    private String clientName;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "oAuthClient")
    private Set<AppUser> users;

    public RegisteredClient toRegisteredClient() {
        return RegisteredClient
                .withId(this.id)
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .clientName(this.clientName)
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(this.clientAuthenticationMethods))
                .authorizationGrantTypes(gt -> gt.addAll(this.authorizationGrantTypes))
                .redirectUris(ru -> ru.addAll(this.redirectUris))
                .scopes(sc -> sc.addAll(this.scopes))
                .clientSettings(
                        ClientSettings
                                .builder()
                                .requireAuthorizationConsent(true)
                                .build()
                )
                .build();
    }
}
