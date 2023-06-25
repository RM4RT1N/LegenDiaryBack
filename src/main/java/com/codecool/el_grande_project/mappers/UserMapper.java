package com.codecool.el_grande_project.mappers;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.DTO.UserDTO;
import com.codecool.el_grande_project.DTO.UserWithPlacesDTO;
import com.codecool.el_grande_project.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class UserMapper {
    public UserDTO mapUserToDTO(UserEntity user){
        return new UserDTO(user.getId(),user.getNickname(), user.getUsername(), user.getAvatar_image_id(), user.getPoints(), user.getAddress_id());
    }

    public UserWithPlacesDTO mapUserAndPlacesToDTOWithPlaces(UserEntity user, List<PlaceDTO> places) {
        return new UserWithPlacesDTO(user.getId(),user.getNickname(),user.getUsername(),user.getAvatar_image_id(),user.getPoints(),user.getAddress_id(),places);
    }
}
