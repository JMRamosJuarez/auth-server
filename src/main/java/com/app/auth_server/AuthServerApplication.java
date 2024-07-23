package com.app.auth_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication /*implements CommandLineRunner*/ {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private RolesRepository rolesRepository;

//    @Autowired
//    private AuthService authService;

//    @Autowired
//    private RegisteredClientRepository registeredClientRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        RegisteredClient rootClient =
//                RegisteredClient.withId(UUID.randomUUID().toString())
//                        .clientId("root-client")
//                        .clientSecret(this.passwordEncoder.encode("root-client-secret"))
//                        .clientName("This 'Root client' is required to register new clients")
//                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                        .scope("client.create")
//                        .scope("client.read")
//                        .build();
//        this.registeredClientRepository.save(rootClient);
//        Role owner = Role.builder().type(RoleType.OWNER).build();
//        this.rolesRepository.save(owner);
//        Role admin = Role.builder().type(RoleType.ADMIN).build();
//        this.rolesRepository.save(admin);
//        Role user = Role.builder().type(RoleType.USER).build();
//        this.rolesRepository.save(user);
//
//        SignUpDto signUpDto = SignUpDto
//                .builder()
//                .username("jmramosjuarez")
//                .password("password")
//                .roles(List.of("OWNER", "ADMIN", "USER"))
//                .build();
//
//        this.authService.signUp(signUpDto);
//    }
}
