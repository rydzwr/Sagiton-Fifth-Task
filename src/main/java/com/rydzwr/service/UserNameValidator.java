package com.rydzwr.service;

import com.rydzwr.exception.InvalidCredentialsException;
import com.rydzwr.model.CustomUserDetails;
import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserNameValidator implements UserDetailsService {
    private final UserRepository repository;

    public UserNameValidator(UserRepository repository) {
        this.repository = repository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (repository.existsByName(username)) {
            return new CustomUserDetails(repository.getUserByName(username));
        }
        throw new InvalidCredentialsException("Invalid Credentials!");
    }
}
