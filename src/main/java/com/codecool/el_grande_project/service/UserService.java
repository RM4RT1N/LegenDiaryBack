package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.DTO.UserDTO;
import com.codecool.el_grande_project.DTO.UserWithPlacesDTO;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.entity.UserEntity;
import com.codecool.el_grande_project.mappers.PlaceMapper;
import com.codecool.el_grande_project.mappers.UserMapper;
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

    public UserService(UserRepository userRepository, UserMapper userMapper, PlaceRepository placeRepository, PlaceMapper placeMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.placeRepository = placeRepository;
        this.placeMapper = placeMapper;
    }

    public UserWithPlacesDTO getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        List<PlaceDTO> places = placeRepository.findPlaceByUser(user).stream().map(placeMapper::mapPlaceToDTO).toList();
        return userMapper.mapUserAndPlacesToDTOWithPlaces(user,places);

    }
}
