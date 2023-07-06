package com.codecool.el_grande_project.DTO;

import lombok.Getter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
public class NewPlaceDTO {
    private UUID userId;
    private Long category_id;
    private double latitude;
    private double longitude;
    private String description;
    private String name;
    private ArrayList<String> imageUrls;

    public NewPlaceDTO(UUID userId, Long category_id, String description, String name, double longitude, double latitude,ArrayList<String> imageUrls) {
        this.userId = userId;
        this.category_id = category_id;
        this.description = description;
        this.name = name;
        this.longitude = longitude;
        this.latitude=latitude;
        this.imageUrls=imageUrls;
    }
}
