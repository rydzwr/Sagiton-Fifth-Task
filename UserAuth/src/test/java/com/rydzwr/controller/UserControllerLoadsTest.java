package com.rydzwr.controller;

import com.rydzwr.UserAuthApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserAuthApplication.class)
@AutoConfigureMockMvc
public class UserControllerLoadsTest {
    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
