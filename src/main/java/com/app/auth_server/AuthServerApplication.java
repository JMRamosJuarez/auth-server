package com.app.auth_server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication implements CommandLineRunner {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private RegisteredClientRepository clientRepository;

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
//        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("root-oauth-client")
//                .clientSecret(this.passwordEncoder.encode("root-oauth-client-secret"))
//                .clientName("Root oauth client")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("http://127.0.0.1:8081/login/oauth2/code/auth-server-client")
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope("client.create")
//                .scope("client.read")
//                .scope("openid")
//                .scope("profile")
//                .scope("email")
//                .build();
//
//        this.clientRepository.save(client);
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
//                .birthdate(LocalDate.of(1991, Month.OCTOBER, 22))
//                .roles(Set.of(owner, admin, user))
//                .build();
//
//        this.appUsersRepository.save(rootAppUser);
    }
}
