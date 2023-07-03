package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
        @Query("SELECT t FROM Token t WHERE t.expieryDate < CURRENT_TIMESTAMP")
        List<Token> findExpiredTokens();

        @Modifying
        @Query("DELETE FROM Token t WHERE t.expieryDate < CURRENT_TIMESTAMP")
        void deleteExpiredTokens();
    }

