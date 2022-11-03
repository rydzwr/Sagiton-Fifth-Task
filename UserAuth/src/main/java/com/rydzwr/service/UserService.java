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

    @Transactional
    public String loginUser(UserDto userDto) {
        if (repository.checkPassword(userDto.getUsername(), userDto.getPassword()) == 0) {
            throw new InvalidCredentialsException("Invalid Credentials!");
        }

        String userSessionCode = UserSessionCodeGenerator.generate();
        repository.updateSessionCode(userDto.getUsername(), userSessionCode);
        return userSessionCode;
    }

    @Transactional
    public void logout(String userSessionCode) {
        if (!sessionCodeValidator.validate(userSessionCode)) {
            throw new UnauthorizedCallException();
        }

        repository.clearUserSession(userSessionCode);
    }

    @Transactional(readOnly = true)
    public UserDto getUserData(String sessionCode) {
        if (!sessionCodeValidator.validate(sessionCode)) {
            throw new UnauthorizedCallException();
        }
        return mapper.mapToUserDto(repository.getUserByUserSessionCode(sessionCode));
    }

    @Transactional(readOnly = true)
    public boolean authoriseRequest(String userSessionCode) {
        return sessionCodeValidator.validate(userSessionCode);
    }
}
