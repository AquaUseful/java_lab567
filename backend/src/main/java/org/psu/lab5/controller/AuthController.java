package org.psu.lab5.controller;

import javax.security.auth.login.LoginException;

import org.psu.lab5.pojo.LoginRequest;
import org.psu.lab5.pojo.LoginResponse;
import org.psu.lab5.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse resp = authService.login(request);
            return ResponseEntity.ok(resp);
        } catch (LoginException e) {
            return ResponseEntity.ok(e);
        }
    }

}
