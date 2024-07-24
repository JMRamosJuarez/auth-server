package com.app.auth_server.repositories;

import com.app.auth_server.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUsersRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);
}
