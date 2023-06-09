package com.codecool.el_grande_project.controller;


import com.codecool.el_grande_project.DTO.UserWithPlacesAndRolesDTO;
import com.codecool.el_grande_project.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")

    public UserWithPlacesAndRolesDTO getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
}
