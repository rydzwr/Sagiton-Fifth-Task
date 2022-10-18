package com.rydzwr.service;

import com.rydzwr.exception.InvalidCredentialsException;
import com.rydzwr.mapper.UserMapper;
import com.rydzwr.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserNameValidator implements UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserNameValidator(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (repository.existsByName(username)) {
            return mapper.mapToUserDto(repository.getUserByName(username));
        }
        throw new InvalidCredentialsException("Invalid Credentials!");
    }
}
