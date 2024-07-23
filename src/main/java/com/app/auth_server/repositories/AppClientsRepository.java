package com.app.auth_server.repositories;

import com.app.auth_server.entities.AppClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppClientsRepository extends JpaRepository<AppClient, String> {

    Optional<AppClient> findByClientId(String id);
}
