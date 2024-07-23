package com.app.auth_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServerApplication {

//	@Autowired
//	private RolesRepository rolesRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Role owner = Role.builder().type(RoleType.OWNER).build();
//		this.rolesRepository.save(owner);
//		Role admin = Role.builder().type(RoleType.ADMIN).build();
//		this.rolesRepository.save(admin);
//		Role user = Role.builder().type(RoleType.USER).build();
//		this.rolesRepository.save(user);
//	}
}
