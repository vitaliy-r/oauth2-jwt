package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth")
public class RegisterController {

  private final UserService userService;

  @PostMapping("/signup")
  public User create(@RequestBody User user) {
    return userService.save(user);
  }

}
