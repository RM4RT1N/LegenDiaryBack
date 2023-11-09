package com.codecool.el_grande_project.mappers;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper {
    public PlaceDTO mapPlaceToDTO(Place place){
        return new PlaceDTO(place.getId(),place.getUser().getId(),place.getCategory_id(), place.getDescription(), place.getName(), place.getLongitude(), place.getLatitude(),place.isApproved());
    }
}
