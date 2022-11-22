package org.psu.lab5.service;

import javax.security.auth.message.AuthException;

import org.psu.lab5.model.User;
import org.psu.lab5.pojo.LoginRequest;
import org.psu.lab5.pojo.LoginResponse;
import org.psu.lab5.pojo.UserNotFound;
import org.psu.lab5.pojo.WrongPassword;
import org.psu.lab5.repository.UserRepository;
import org.psu.lab5.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponse login(@NonNull LoginRequest request) throws AuthException {
        final User user = userRepository.getByUsername(request.getUsername())
                .orElseThrow(() -> new AuthException("User not found"));
        if (user.getPassword().equals(request.getPassword())) {
            final String token = jwtUtils.generate(user);
            return new LoginResponse(token);
        } else {
            throw new AuthException("Wrong password");
        }
    }

}
