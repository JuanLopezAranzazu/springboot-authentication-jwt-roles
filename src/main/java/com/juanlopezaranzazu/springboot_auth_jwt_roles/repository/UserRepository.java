package com.juanlopezaranzazu.springboot_auth_jwt_roles.repository;

import com.juanlopezaranzazu.springboot_auth_jwt_roles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // obtener usuario por username
    User findByUsername(String username);
}
