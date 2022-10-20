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
    // HandlerMethodArgumentResolver -  UserSessionCodeMethodArgumentResolver
    // w nim wyciągasz z Requesta Header UserSessionCode i zwracasz jako string
    // @UserCode String userSessionCode
    public ResponseEntity<UserDto> getUserData(@UserCode(value = "UserSessionCode") String userSessionCode) {
        return ResponseEntity.ok(service.getUserData(userSessionCode));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(@UserCode(value = "UserSessionCode") String userSessionCode) {
        service.logout(userSessionCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/authorize")
    public ResponseEntity<Boolean> authorize(@UserCode(value = "UserSessionCode") String userSessionCode){
        return ResponseEntity.ok(service.authoriseRequest(userSessionCode));
    }
}
