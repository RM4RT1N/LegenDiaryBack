package com.codecool.el_grande_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "token_blacklist")
@Entity
public class Token {
    @Id
    private UUID id=UUID.randomUUID();
    private String token;
    private Date expieryDate;

    public Token(String token, Date expieryDate) {
        this.token = token;
        this.expieryDate = expieryDate;
    }
}
