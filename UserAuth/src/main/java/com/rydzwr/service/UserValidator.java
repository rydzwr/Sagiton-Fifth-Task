package com.rydzwr.service;

import com.rydzwr.mapper.UserMapper;
import com.rydzwr.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserValidator implements UserDetailsService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserValidator(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (repository.existsByName(username)) {
            return mapper.mapToUserDto(repository.getUserByName(username));
        }
        throw new UsernameNotFoundException("User with given name doesn't exist!");
    }
}
