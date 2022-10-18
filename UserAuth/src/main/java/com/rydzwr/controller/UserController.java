package com.rydzwr.controller;

import com.rydzwr.dto.UserDto;
import com.rydzwr.exception.UnauthorizedCallException;
import com.rydzwr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(service.loginUser(userDto));
    }

    @GetMapping(value = "/userDetails")
    public ResponseEntity<UserDto> getUserData(@RequestHeader String UserSessionCode) {
        return ResponseEntity.ok(service.getUserData(UserSessionCode));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(@RequestHeader String UserSessionCode) {
        service.logout(UserSessionCode);
        return ResponseEntity.ok().build();
    }
}
