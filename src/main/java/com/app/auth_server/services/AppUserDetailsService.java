package com.app.auth_server.services;

import com.app.auth_server.entities.AppUser;
import com.app.auth_server.repositories.AppUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usersRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public OidcUserInfo getUserInfo(String username) {
        AppUser user = this.usersRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return OidcUserInfo.builder()
                .subject(username)
                .name(user.getName())
                .email(user.getEmail())
                .birthdate(user.getBirthDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
}
