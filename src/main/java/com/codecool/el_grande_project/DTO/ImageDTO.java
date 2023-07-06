package com.codecool.el_grande_project.DTO;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ImageDTO {
    private UUID place_id;
    private String imageUrl;

    public ImageDTO(UUID place_id, String imageUrl) {
        this.place_id = place_id;
        this.imageUrl= imageUrl;
    }
}
