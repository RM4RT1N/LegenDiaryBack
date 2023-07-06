package com.codecool.el_grande_project.DTO;

import java.util.List;
import java.util.UUID;

public record UserWithPlacesAndRolesDTO(
        UUID id,
        String nickname,
        String username,
        Long avatar_image_id,
        Integer points,
        Long address_id,
        List<PlaceDTO> places,
        List<RoleDTO> roles
) {
}
