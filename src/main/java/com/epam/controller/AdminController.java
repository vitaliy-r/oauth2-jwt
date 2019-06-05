package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
