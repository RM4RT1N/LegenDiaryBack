package com.codecool.el_grande_project.DTO;

import lombok.Data;

import java.util.UUID;
@Data
public class PlaceDTO {
    private UUID id;
    private UUID userId;
    private Long category_id;
    private double latitude;
    private double longitude;
    private String description;
    private String name;
    private boolean approved;

    public PlaceDTO(UUID id, UUID userId, Long category_id, String description, String name, double longitude, double latitude,boolean approved) {
        this.id = id;
        this.userId = userId;
        this.category_id = category_id;
        this.description = description;
        this.name = name;
        this.longitude = longitude;
        this.latitude=latitude;
        this.approved=approved;
    }
}
