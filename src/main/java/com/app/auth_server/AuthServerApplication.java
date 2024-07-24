package com.app.auth_server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication implements CommandLineRunner {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private OAuthClientsRepository oAuthClientsRepository;

//    @Autowired
//    private RolesRepository rolesRepository;

//    @Autowired
//    private AppUsersRepository appUsersRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        //(1) Create the root-oauth-client
//        OAuthClient rootClient = this.oAuthClientsRepository.save(
//                OAuthClient.builder()
//                        .id(UUID.randomUUID().toString())
//                        .clientId("root-oauth-client")
//                        .clientSecret(this.passwordEncoder.encode("root-oauth-client-secret"))
//                        .clientName("Root oauth client")
//                        .clientAuthenticationMethods(
//                                Set.of(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                        )
//                        .authorizationGrantTypes(
//                                Set.of(
//                                        AuthorizationGrantType.CLIENT_CREDENTIALS,
//                                        AuthorizationGrantType.AUTHORIZATION_CODE,
//                                        AuthorizationGrantType.REFRESH_TOKEN
//                                )
//                        )
//                        .redirectUris(
//                                Set.of(
//                                        //Your custom client
//                                        "http://127.0.0.1:8081/login/oauth2/code/auth-server-client",
//                                        //Postman callback url
//                                        "https://oauth.pstmn.io/v1/callback"
//                                )
//                        )
//                        .scopes(
//                                Set.of(
//                                        "client.create",
//                                        "client.read",
//                                        "openid",
//                                        "profile",
//                                        "email"
//                                )
//                        ).build()
//        );
//
//        //(2) Create the user roles
//        final Role owner = this.rolesRepository.save(
//                Role.builder().type(RoleType.OWNER).build()
//        );
//
//        final Role admin = this.rolesRepository.save(
//                Role.builder().type(RoleType.ADMIN).build()
//        );
//
//        final Role user = this.rolesRepository.save(
//                Role.builder().type(RoleType.USER).build()
//        );
//
//        //(3) Create an app user
//        final AppUser rootAppUser = AppUser.builder()
//                .username("root")
//                .password(this.passwordEncoder.encode("password"))
//                .name("Root app user")
//                .email("root@email.com")
//                .birthDay(LocalDate.of(1991, Month.OCTOBER, 22))
//                .roles(Set.of(owner, admin, user))
//                .oAuthClient(rootClient)
//                .build();
//
//        this.appUsersRepository.save(rootAppUser);
    }
}
