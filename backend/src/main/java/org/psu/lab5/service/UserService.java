package org.psu.lab5.service;

import org.psu.lab5.authentication.JwtAuthentication;
import org.psu.lab5.model.BinFile;
import org.psu.lab5.model.User;
import org.psu.lab5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BinfileService binfileService;
    @Autowired
    AuthService authService;

    public User getByUsername(String username) {
        return userRepository.getByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public JwtAuthentication authInfo() {
        return authService.getAuthInfo();
    }

    public boolean hasFile(String username) {
        return this.getByUsername(username).getFile() != null;
    }

    public void addAvatar(BinFile avatarFile) {

    }

}
