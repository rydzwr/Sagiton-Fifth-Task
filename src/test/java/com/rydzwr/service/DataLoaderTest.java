package com.rydzwr.service;

import com.rydzwr.UserAuthApplication;
import com.rydzwr.model.User;
import com.rydzwr.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAuthApplication.class)
public class DataLoaderTest {

    private final List<String> validUserNames = asList("rootName", "adminName", "testName");

    @Autowired
    private UserRepository userRepository;

    @Test
    public void listsShouldBeEqual() {
        List<String> usersNames = userRepository.findAll().stream()
                .map(User::getName)
                .collect(Collectors.toList());
        assertThat(usersNames.equals(validUserNames));
    }
}
