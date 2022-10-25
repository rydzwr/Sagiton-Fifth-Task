package com.rydzwr.service;

import com.rydzwr.UserAuthApplication;
import com.rydzwr.dto.UserDto;
import com.rydzwr.exception.InvalidCredentialsException;
import com.rydzwr.exception.UnauthorizedCallException;
import com.rydzwr.model.User;
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
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Test
    public void shouldReturnUserSessionCode() {
        // GIVEN
        UserDto userDto = new UserDto("testName", "testPassword");

        // WHEN
        String userSessionCode = service.loginUser(userDto);

        // THEN
        assertNotNull(userSessionCode);

        // CLEANUP
        User user = repository.getUserByUserSessionCode(userSessionCode);
        user.setUserSessionCode("123");
        repository.save(user);
    }

    @Test
    public void shouldThrowInvalidCredentialsException() {
        // GIVEN
        UserDto userDto = new UserDto("testName", "foo");

        // WHEN = THEN
        var exception = catchThrowable(() ->  service.loginUser(userDto));
        assertThat(exception)
                .isInstanceOf(InvalidCredentialsException.class)
                .hasMessageContaining("Invalid Credentials!");
    }

    @Test
    public void userSessionCodeShouldBeNull() {
        // GIVEN
        String userSessionCode = "123";

        // WHEN
        service.logout(userSessionCode);

        // THEN
        assertNull(repository.getUserByName("testName").getUserSessionCode());

        // CLEANUP
        User test = repository.getUserByName("testName");
        test.setUserSessionCode(userSessionCode);
        repository.save(test);
    }

    @Test
    public void shouldThrowUnauthorizedCallException() {
        // GIVEN
        String userSessionCode = "foo";

        // WHEN = THEN
        var exception = catchThrowable(() ->  service.logout(userSessionCode));
        assertThat(exception)
                .isInstanceOf(UnauthorizedCallException.class)
                .hasMessageContaining("Unauthorized!");
    }

    @Test
    public void shouldReturnUserDto() {
        // GIVEN
        String userSessionCode = "123";

        // WHEN
        UserDto toTest = service.getUserData(userSessionCode);

        // THEN
        assertEquals("testName", toTest.getUsername());
    }

    @Test
    public void shouldThrowUnauthorizedCallExceptionByGetUserDataMethod() {
        // GIVEN
        String userSessionCode = "foo";

        // WHEN = THEN
        var exception = catchThrowable(() ->  service.getUserData(userSessionCode));
        assertThat(exception)
                .isInstanceOf(UnauthorizedCallException.class)
                .hasMessageContaining("Unauthorized!");
    }
}
