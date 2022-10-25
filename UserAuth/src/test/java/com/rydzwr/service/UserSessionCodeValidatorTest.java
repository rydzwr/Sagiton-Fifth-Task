package com.rydzwr.service;

import com.rydzwr.UserAuthApplication;
import com.rydzwr.exception.InvalidCredentialsException;
import com.rydzwr.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAuthApplication.class)
public class UserSessionCodeValidatorTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionCodeValidator validator;

    @Test
    public void shouldReturnValidUser() {
        assertTrue(validator.validate("123"));
    }

    @Test
    public void shouldReturnFalse() {
        assertFalse(validator.validate("foo"));
    }
}
