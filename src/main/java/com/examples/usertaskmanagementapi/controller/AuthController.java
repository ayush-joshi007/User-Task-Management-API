package com.examples.usertaskmanagementapi.controller;

import com.examples.usertaskmanagementapi.dto.request.RegisterRequest;
import com.examples.usertaskmanagementapi.entity.User;
import com.examples.usertaskmanagementapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

}
