package com.codecool.el_grande_project.DTO;

import lombok.Getter;

@Getter
public class PlaceDTO {
    private Long added_by_id_user;
    private Long category_id;
    private double latitude;
    private double longitude;
    private String description;
    private String name;

    public PlaceDTO(Long added_by_id_user, Long category_id, String description,String name, double longitude,double latitude) {
        this.added_by_id_user = added_by_id_user;
        this.category_id = category_id;
        this.description = description;
        this.name = name;
        this.longitude = longitude;
        this.latitude=latitude;
    }
}
