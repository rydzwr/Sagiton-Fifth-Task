package com.rydzwr.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rydzwr.UserAuthApplication;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = UserAuthApplication.class)
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTrue() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/authorize").headers(getHeader("123")))
                .andDo(print())
                .andExpect(content().string("true"));
    }

    @Test
    public void shouldReturnUnauthorized() throws Exception {
        this.mockMvc.perform(get("/api/v1/authorize").headers(getHeader("foo")))
                .andExpect((status().isForbidden()));
    }

    @Test
    public void shouldReturnSuccessful() throws Exception {
        String testJson = """
                {
                    "name": "testName",
                    "password": "testPassword"
                }
                """;
        this.mockMvc.perform(
                        post("/api/v1/login")
                                .contentType(APPLICATION_JSON)
                                .content(testJson))
                .andDo(print()).andExpect((status()).is2xxSuccessful());
    }

    @Test
    public void shouldReturnUserDetails() throws Exception {
        String testJson = """
                {
                    "name": "testName",
                    "password": "testPassword"
                }
                """;
        this.mockMvc.perform(
                        get("/api/v1/userDetails")
                                .headers(getHeader("123"))
                                .contentType(APPLICATION_JSON)
                                .content(testJson))
                .andExpect((status()).is2xxSuccessful());
    }

    private HttpHeaders getHeader(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("UserSessionCode", value);
        return headers;
    }
}
