package com.rydzwr.filter;

import com.rydzwr.UserAuthApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = UserAuthApplication.class)
@AutoConfigureMockMvc
public class LoggedUsersFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnUnauthorized() throws Exception {
        this.mockMvc.perform(get("/api/v1/authorize").headers(getHeader("foo")))
                .andExpect((status().isForbidden()));
    }

    @Test
    public void shouldReturnTrue() throws Exception {
        this.mockMvc.perform(
                        get("/api/v1/authorize").headers(getHeader("123")))
                .andDo(print())
                .andExpect(content().string("true"));
    }

    private HttpHeaders getHeader(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("UserSessionCode", value);
        return headers;
    }
}
