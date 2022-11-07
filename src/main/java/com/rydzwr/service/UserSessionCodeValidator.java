package com.rydzwr.service;

import com.rydzwr.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSessionCodeValidator {

    private final UserRepository repository;

    public UserSessionCodeValidator(UserRepository repository) {
        this.repository = repository;
    }

    public boolean validate(String sessionCode) {
        return repository.existsByUserSessionCode(sessionCode);
    }
}
