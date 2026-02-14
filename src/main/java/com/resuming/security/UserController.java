package com.resuming.security;

import com.resuming.security.model.Users;
import com.resuming.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }
    @PostMapping
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }
}
