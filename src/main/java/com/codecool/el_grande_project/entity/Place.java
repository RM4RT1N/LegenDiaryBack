package com.codecool.el_grande_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place {
    @Id
    private UUID id =UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Long category_id;
    private double latitude;
    private double longitude;
    private String description;
    private String name;



    public Place(UserEntity user, Long category_id, double latitude, double longitude, String description, String name) {
        this.user = user;
        this.category_id = category_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.name = name;
    }
}
