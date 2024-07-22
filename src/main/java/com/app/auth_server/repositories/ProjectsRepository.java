package com.app.auth_server.repositories;

import com.app.auth_server.entities.AppProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectsRepository extends JpaRepository<AppProject, String> {

    Optional<AppProject> findByClientId(String id);
}
