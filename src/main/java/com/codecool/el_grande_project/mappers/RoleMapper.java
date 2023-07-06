package com.codecool.el_grande_project.mappers;

import com.codecool.el_grande_project.DTO.RoleDTO;
import com.codecool.el_grande_project.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoleMapper {
    public RoleDTO mapRolesToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }
}
