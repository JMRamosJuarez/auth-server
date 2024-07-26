package com.app.auth_server.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Data
@Configuration
@ConfigurationProperties(prefix = "oauth-client")
public class OAuthClientSettings {
    private boolean requireProofKey;
    private boolean requireAuthorizationConsent;

    public ClientSettings getClientSettings() {
        return ClientSettings
                .builder()
                .requireProofKey(this.requireProofKey)
                .requireAuthorizationConsent(this.requireAuthorizationConsent)
                .build();
    }
}
