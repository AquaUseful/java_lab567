package org.psu.lab5.utils;

import java.util.Collections;
import java.util.Set;

import org.psu.lab5.model.Role;
import org.psu.lab5.model.User;
import org.psu.lab5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            userRepository.save(new User(0,
                    "admin",
                    "admin",
                    Collections.singleton(Role.ADMIN)));
        } catch (DataIntegrityViolationException e) {

        }
    }

}
