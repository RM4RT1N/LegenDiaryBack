package com.codecool.el_grande_project.DTO;

import lombok.Getter;


@Getter
public class CityDTO {
    private String city;
    private float longitude;
    private float latitude;

    public CityDTO(String city,float longitude, float latitude) {
        this.city=city;
        this.longitude = longitude;
        this.latitude=latitude;
    }
}
