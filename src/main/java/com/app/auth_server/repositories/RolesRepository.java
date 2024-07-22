package com.app.auth_server.repositories;

import com.app.auth_server.entities.Role;
import com.app.auth_server.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByType(RoleType type);
}
