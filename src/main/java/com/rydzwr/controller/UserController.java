package com.rydzwr.controller;

import com.rydzwr.model.JsonResponse;
import com.rydzwr.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/login")
    public JsonResponse login() {
        String auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (auth.contains("ADMIN")) {
            return new JsonResponse("admin");
        }
        return new JsonResponse("user");
    }
}
