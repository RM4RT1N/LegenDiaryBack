package com.codecool.el_grande_project.mappers;

import com.codecool.el_grande_project.DTO.UserDTO;
import com.codecool.el_grande_project.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
    public UserDTO mapUserToDTO(UserEntity user){
        return new UserDTO(user.getNickname(), user.getUsername(), user.getAvatar_image_id(), user.getPoints(), user.getAddress_id());
    }

}
