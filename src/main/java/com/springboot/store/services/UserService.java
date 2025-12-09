package com.springboot.store.services;

import com.springboot.store.dto.LoginRequest;
import com.springboot.store.dto.RegisterRequest;
import com.springboot.store.repositories.UserRepository;
import com.springboot.store.Role;
import com.springboot.store.User;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //  REGISTER USER
    public String register(RegisterRequest request) {

        // Check duplicate username
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already taken");
        }

        //  Create new user entity
        User user = new User();
        user.setUsername(request.username());

        // Encode password
        user.setPassword(passwordEncoder.encode(request.password()));

        //  Convert string role → Enum
        user.setRole(Role.valueOf(request.role().toUpperCase())); // e.g. ADMIN, ENGINEER

        //  Save in DB
        userRepository.save(user);

        return "User registered successfully";
    }

    //  LOGIN USER
    public User login(LoginRequest request) {

        //  Check username exists
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        //  Check password
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user; 
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    
}
