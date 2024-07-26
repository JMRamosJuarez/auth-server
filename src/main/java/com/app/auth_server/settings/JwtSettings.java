package com.app.auth_server.settings;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtSettings {
    private int accessTokenTimeToLive;
    private int refreshTokenTimeToLive;

    public TokenSettings getTokenSettings() {
        return TokenSettings.builder()
                .accessTokenTimeToLive(
                        Duration.ofSeconds(this.accessTokenTimeToLive)
                )
                .refreshTokenTimeToLive(
                        Duration.ofSeconds(this.refreshTokenTimeToLive)
                )
                .build();
    }
}
