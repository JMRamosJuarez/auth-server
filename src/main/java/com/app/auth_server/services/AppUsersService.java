package com.app.auth_server.services;

import com.app.auth_server.dto.AppUserDto;
import com.app.auth_server.dto.CreateAppUserDto;
import com.app.auth_server.entities.AppUser;
import com.app.auth_server.entities.Role;
import com.app.auth_server.errors.RoleNotFoundException;
import com.app.auth_server.repositories.AppUsersRepository;
import com.app.auth_server.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUsersService implements UserDetailsService {

    private final AppUsersRepository usersRepository;

    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.usersRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    public AppUserDto signUp(CreateAppUserDto createAppUserDto) {

        final Set<Role> roles = createAppUserDto.getRoles()
                .stream()
                .map(
                        r -> this.rolesRepository
                                .findByType(r)
                                .orElseThrow(
                                        () -> new RoleNotFoundException(
                                                "Role not found %s".formatted(r.name())
                                        )
                                )
                )
                .collect(Collectors.toSet());

        final AppUser user = this.usersRepository.save(
                AppUser
                        .builder()
                        .username(createAppUserDto.getUsername())
                        .password(this.passwordEncoder.encode(createAppUserDto.getPassword()))
                        .name(createAppUserDto.getName())
                        .email(createAppUserDto.getEmail())
                        .birthdate(createAppUserDto.getBirthdate())
                        .roles(roles)
                        .build()
        );

        return user.toDto();
    }

    public OidcUserInfo getUserInfo(String username) {
        final AppUser user = this.usersRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        return OidcUserInfo.builder()
                .subject(user.getId().toString())
                .name(user.getName())
                .email(user.getEmail())
                .birthdate(user.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
}
