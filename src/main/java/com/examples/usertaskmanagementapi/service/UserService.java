package com.examples.usertaskmanagementapi.service;

import com.examples.usertaskmanagementapi.dto.request.RegisterRequest;
import com.examples.usertaskmanagementapi.entity.Role;
import com.examples.usertaskmanagementapi.entity.User;
import com.examples.usertaskmanagementapi.exception.DuplicateResourceException;
import com.examples.usertaskmanagementapi.repository.RoleRepository;
import com.examples.usertaskmanagementapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(RegisterRequest registerRequest){
        if (userRepository.existsByUsername((registerRequest.getUsername()))) {
            throw new DuplicateResourceException("Username already exists");
        }

        if (userRepository.existsByEmail((registerRequest.getEmail()))) {
            throw new DuplicateResourceException("Email already exists");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow();

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(true);
        user.setCreatedAt(Instant.now());
        user.getRoles().add(userRole);

        return userRepository.save(user);
    }
}
