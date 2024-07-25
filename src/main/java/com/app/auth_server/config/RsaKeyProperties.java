package com.app.auth_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Configuration
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
    private String kid;
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}
