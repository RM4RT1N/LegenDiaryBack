package com.codecool.el_grande_project.DTO;

public record UserDTO(
        String nickname,
        String username,
        Long avatar_image_id,
        Integer points,
        Long address_id
) {
}
