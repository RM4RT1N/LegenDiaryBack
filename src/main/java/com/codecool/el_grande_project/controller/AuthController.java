package com.codecool.el_grande_project.controller;

import com.codecool.el_grande_project.DTO.AuthResponseDTO;
import com.codecool.el_grande_project.DTO.LoginDTO;
import com.codecool.el_grande_project.DTO.RegisterDTO;
import com.codecool.el_grande_project.DTO.TokenDTO;
import com.codecool.el_grande_project.entity.Role;
import com.codecool.el_grande_project.entity.UserEntity;
import com.codecool.el_grande_project.repository.RoleRepository;
import com.codecool.el_grande_project.repository.UserRepository;
import com.codecool.el_grande_project.security.JwtGenerator;
import com.codecool.el_grande_project.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    private TokenService tokenService;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if (userRepository.existsByUsername(registerDTO.username())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user= new UserEntity();
        user.setUsername(registerDTO.username());
        user.setPassword(passwordEncoder.encode(registerDTO.password()));
        Role role = roleRepository.findRoleByName(registerDTO.role()).get();
        user.getRoles().add(role);
        userRepository.save(user);
        return new ResponseEntity<>("User added correctly", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }

    @PostMapping("/logout")
    public  ResponseEntity<String> logout(@RequestBody TokenDTO token){
        tokenService.addTokenToBlackList(token);
        return new ResponseEntity<>("Loged out correctly", HttpStatus.OK);
    }

}
