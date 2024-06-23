package com.crio.orbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.orbs.entity.User;
import com.crio.orbs.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
     @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getRole());
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        String token = userService.authenticateUser(email, password);
        return ResponseEntity.ok(token);
    }
}
