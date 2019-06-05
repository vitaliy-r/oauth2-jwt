package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public User findUser(@PathVariable String userId) {
        return userService.findById(userId);
    }

    @DeleteMapping("/users/{userId}")
    public String delete(@PathVariable String userId) {
        userService.deleteById(userId);
        return "success";
    }

}
