package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<UserEntity> findById(UUID id);
}
