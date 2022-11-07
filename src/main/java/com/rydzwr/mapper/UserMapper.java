package com.rydzwr.mapper;

import com.rydzwr.dto.UserDto;
import com.rydzwr.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getEmail()
        );
    }
}