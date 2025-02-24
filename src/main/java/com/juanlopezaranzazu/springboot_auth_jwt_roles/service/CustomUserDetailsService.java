package com.juanlopezaranzazu.springboot_auth_jwt_roles.service;

import com.juanlopezaranzazu.springboot_auth_jwt_roles.entity.User;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.entity.UserPrincipal;
import com.juanlopezaranzazu.springboot_auth_jwt_roles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Usuario no encontrado");
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new UserPrincipal(user);
    }
}
