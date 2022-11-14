package com.rydzwr.controller;

import com.rydzwr.model.UserAuthResponse;
import com.rydzwr.model.UserDataResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/login")
    public UserAuthResponse login() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        return new UserAuthResponse(role);
    }

    @GetMapping("/data/user")
    public UserDataResponse home() {
        return new UserDataResponse("user public data");
    }

    @GetMapping("/data/admin")
    public UserDataResponse admin() {
        return new UserDataResponse("admin only data");
    }
}
