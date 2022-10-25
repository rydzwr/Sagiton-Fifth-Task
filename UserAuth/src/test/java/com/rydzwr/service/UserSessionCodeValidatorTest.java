package com.rydzwr.service;

import com.rydzwr.UserAuthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAuthApplication.class)
public class UserSessionCodeValidatorTest {

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
