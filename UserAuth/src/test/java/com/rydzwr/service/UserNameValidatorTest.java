package com.rydzwr.service;

import com.rydzwr.UserAuthApplication;
import com.rydzwr.exception.InvalidCredentialsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAuthApplication.class)
public class UserNameValidatorTest {

    @Autowired
    private UserNameValidator validator;

    @Test
    public void shouldReturnValidUser() {
        assertNotNull(validator.loadUserByUsername("testName"));
    }

    @Test
    public void shouldThrowAnError() {
        var exception = catchThrowable(() ->  assertNotNull(validator.loadUserByUsername("foo")));
        assertThat(exception)
                .isInstanceOf(InvalidCredentialsException.class)
                .hasMessageContaining("Invalid Credentials!");
    }
}
