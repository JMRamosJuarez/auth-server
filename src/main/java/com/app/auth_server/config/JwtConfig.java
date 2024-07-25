package com.app.auth_server.config;

import com.app.auth_server.services.AppUsersService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    private final RsaKeyProperties rsaKeyProperties;

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(AppUsersService usersService) {
        return context -> {
            final OAuth2TokenType tokenType = context.getTokenType();
            final Authentication principal = context.getPrincipal();

            if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {
                final Set<String> roles = principal
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                context.getClaims().claim("roles", roles);
            }

            if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType) && !(principal instanceof OAuth2ClientAuthenticationToken)) {
                final OidcUserInfo userInfo = usersService.getUserInfo(principal.getName());
                context.getClaims().claims(c -> c.putAll(userInfo.getClaims()));
            }

            if (OidcParameterNames.ID_TOKEN.equals(tokenType.getValue())) {
                final OidcUserInfo userInfo = usersService.getUserInfo(principal.getName());
                context.getClaims().claims(c -> c.putAll(userInfo.getClaims()));
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        final RSAKey rsaKey = new RSAKey
                .Builder(this.rsaKeyProperties.getPublicKey())
                .privateKey(this.rsaKeyProperties.getPrivateKey())
                .keyID(this.rsaKeyProperties.getKid())
                .build();
        final JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }
}
