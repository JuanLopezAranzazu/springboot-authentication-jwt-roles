package com.juanlopezaranzazu.springboot_auth_jwt_roles.service;

import com.juanlopezaranzazu.springboot_auth_jwt_roles.dto.AuthResponseDTO;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.dto.LoginRequestDTO;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.dto.RegisterRequestDTO;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.entity.Role;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.repository.RoleRepository;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.repository.UserRepository;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    // login de usuarios con jwt
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO){
        // verificar las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );
        // generar token
        String token = jwtService.generateToken(loginRequestDTO.getUsername());;
        return new AuthResponseDTO(token);
    }

    // registro de usuario con jwt
    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO){
        // verificar el username
        if (userRepository.findByUsername(registerRequestDTO.getUsername()) != null) {
            throw new RuntimeException("El usuario ya existe");
        }

        // verificar el rol
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("El rol USER no está definido en la base de datos"));

        // crear el usuario
        User newUser = new User();
        newUser.setName(registerRequestDTO.getName());
        newUser.setUsername(registerRequestDTO.getUsername());
        // encriptar contraseña
        newUser.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
        // asignar roles
        newUser.getRoles().add(userRole);
        // guardar usuario
        userRepository.save(newUser);

        // generar token
        String token = jwtService.generateToken(registerRequestDTO.getUsername());;
        return new AuthResponseDTO(token);
    }
}
