package com.rydzwr.service;

import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        userRepository.deleteAll();
        userRepository.save(new User("user",passwordEncoder.encode("user123"),"USER"));
        userRepository.save(new User("admin",passwordEncoder.encode("admin123"),"ADMIN"));
    }
}
