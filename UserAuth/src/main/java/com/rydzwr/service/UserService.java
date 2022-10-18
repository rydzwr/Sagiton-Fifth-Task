package com.rydzwr.service;

import com.rydzwr.dto.UserDto;
import com.rydzwr.exception.InvalidCredentialsException;
import com.rydzwr.exception.UnauthorizedCallException;
import com.rydzwr.mapper.UserMapper;
import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService {
    private final UserNameValidator nameValidator;
    private final UserMapper mapper;
    private final UserRepository repository;
    private final UserSessionCodeValidator sessionCodeValidator;

    public UserService(UserNameValidator nameValidator, UserMapper mapper, UserRepository repository, UserSessionCodeValidator sessionCodeValidator) {
        this.nameValidator = nameValidator;
        this.mapper = mapper;
        this.repository = repository;
        this.sessionCodeValidator = sessionCodeValidator;
    }

    public String loginUser(UserDto userDto) {
        User user = mapper.mapToUser((UserDto) nameValidator.loadUserByUsername(userDto.getUsername()));

        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials!");
        }

        user.setUserSessionCode(UserSessionCodeGenerator.generate());
        repository.save(user);
        return user.getUserSessionCode();
    }

    public void logout(String userSessionCode) {
        User user = repository.getUserByUserSessionCode(userSessionCode);
        user.setUserSessionCode(null);
        repository.save(user);
    }

    public UserDto getUserData(String sessionCode) {
        return mapper.mapToUserDto(repository.getUserByUserSessionCode(sessionCode));
    }

    public boolean authoriseRequest(String userSessionCode) {
        return sessionCodeValidator.validate(userSessionCode);
    }
}
