package com.codecool.el_grande_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    private Long id;

    private String name;
}
