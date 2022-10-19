package com.rydzwr.service;

import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        userRepository.deleteAll();
        userRepository.save(new User("rootName", "rootSurname", "rootEmail", "rootPassword", null));
        userRepository.save(new User("adminName", "adminSurname", "adminEmail", "adminPassword", null));
    }
}
