package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.DTO.RoleDTO;
import com.codecool.el_grande_project.DTO.UserWithPlacesAndRolesDTO;
import com.codecool.el_grande_project.entity.UserEntity;
import com.codecool.el_grande_project.mappers.PlaceMapper;
import com.codecool.el_grande_project.mappers.UserMapper;
import com.codecool.el_grande_project.mappers.RoleMapper;
import com.codecool.el_grande_project.repository.PlaceRepository;
import com.codecool.el_grande_project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    private final PlaceMapper placeMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, PlaceRepository placeRepository, PlaceMapper placeMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
        this.roleMapper = roleMapper;
    }

    public UserWithPlacesAndRolesDTO getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        List<RoleDTO> roles = user.getRoles().stream().map(roleMapper::mapRolesToDTO).toList();
        List<PlaceDTO> places = placeRepository.findPlaceByUser(user).stream().map(placeMapper::mapPlaceToDTO).toList();
        return userMapper.mapUserAndPlacesToDTOWithPlaces(user,places,roles);

    }
}
