package com.codecool.el_grande_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Image {
    @Id
    private UUID id =UUID.randomUUID();
    private UUID place_id;
    private String imageUrl;

    public Image() {

    }

    public Image(UUID place_id, String imageUrl) {
        this.place_id = place_id;
        this.imageUrl = imageUrl;
    }
}