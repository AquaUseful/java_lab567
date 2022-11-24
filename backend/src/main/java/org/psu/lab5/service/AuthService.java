package org.psu.lab5.service;

import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;
import javax.validation.Valid;

import org.psu.lab5.authentication.JwtAuthentication;
import org.psu.lab5.exception.UserNotFoundException;
import org.psu.lab5.exception.WrongPasswordException;
import org.psu.lab5.model.User;
import org.psu.lab5.pojo.LoginRequest;
import org.psu.lab5.pojo.LoginResponse;
import org.psu.lab5.pojo.RegisterRequest;
import org.psu.lab5.pojo.RegisterResponse;
import org.psu.lab5.repository.UserRepository;
import org.psu.lab5.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponse login(@NonNull @Valid LoginRequest request)
            throws UserNotFoundException, WrongPasswordException {
        final User user = userRepository.getByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException());
        if (user.getPassword().equals(request.getPassword())) {
            final String token = jwtUtils.generate(user);
            return new LoginResponse(token);
        } else {
            throw new WrongPasswordException();
        }
    }

    public RegisterResponse register(@NonNull @Valid RegisterRequest request) {
        return new RegisterResponse();
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
