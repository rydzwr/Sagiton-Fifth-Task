package com.rydzwr.service;

import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {


        String rootPassword = encoder.encode("rootPassword");
        String adminPassword = encoder.encode("adminPassword");
        String testPassword = encoder.encode("testPassword");

        userRepository.deleteAll();
        userRepository.save(new User("rootName", "rootSurname", "rootEmail", rootPassword, null, "USER"));
        userRepository.save(new User("adminName", "adminSurname", "adminEmail", adminPassword, null, "ADMIN"));
        userRepository.save(new User("testName", "testSurname", "testEmail", testPassword, "123", "USER"));
    }
}
