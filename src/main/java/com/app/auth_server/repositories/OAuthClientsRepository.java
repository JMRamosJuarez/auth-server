package com.app.auth_server.repositories;

import com.app.auth_server.entities.OAuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthClientsRepository extends JpaRepository<OAuthClient, String> {

    Optional<OAuthClient> findByClientId(String id);
}
