package com.juanlopezaranzazu.springboot_auth_jwt_roles.repository;

import com.juanlopezaranzazu.springboot_auth_jwt_roles.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // obtener rol por nombre
    Optional<Role> findByName(String name);
}
