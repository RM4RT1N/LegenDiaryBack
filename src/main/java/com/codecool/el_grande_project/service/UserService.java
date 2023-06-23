package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.DTO.UserDTO;
import com.codecool.el_grande_project.entity.UserEntity;
import com.codecool.el_grande_project.mappers.UserMapper;
import com.codecool.el_grande_project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        return userMapper.mapUserToDTO(user);

    }
}
