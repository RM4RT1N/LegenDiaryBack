package com.codecool.el_grande_project.DTO;

import lombok.Getter;

@Getter
public class ImageDTO {
    private Long place_id;
    private String imageUrl;

    public ImageDTO(Long place_id, String imageUrl) {
        this.place_id = place_id;
        this.imageUrl= imageUrl;
    }
}
