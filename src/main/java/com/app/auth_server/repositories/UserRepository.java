package com.app.auth_server.repositories;

import com.app.auth_server.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String email);
}
