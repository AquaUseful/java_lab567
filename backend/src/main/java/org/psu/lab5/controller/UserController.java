package org.psu.lab5.controller;

import java.io.Console;

import org.psu.lab5.authentication.JwtAuthentication;
import org.psu.lab5.model.User;
import org.psu.lab5.repository.UserRepository;
import org.psu.lab5.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<User> selfInfo() {
        final JwtAuthentication auth = authService.getAuthInfo();
        System.out.println(auth.getName());
        System.out.println(auth.getRoles());
        return ResponseEntity
                .ok(userRepository.getByUsername(auth.getName()).orElseThrow(() -> new RuntimeException()));
    }

}
