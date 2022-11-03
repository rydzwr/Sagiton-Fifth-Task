package com.rydzwr.controller;

import com.rydzwr.dto.UserDto;
import com.rydzwr.model.UserCode;
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
    public ResponseEntity<UserDto> getUserData(@UserCode String userSessionCode) {
        return ResponseEntity.ok(service.getUserData(userSessionCode));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(@UserCode String userSessionCode) {
        service.logout(userSessionCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/authorize")
    public ResponseEntity<Boolean> authorize(@UserCode String userSessionCode){
        return ResponseEntity.ok(service.authoriseRequest(userSessionCode));
    }
}
