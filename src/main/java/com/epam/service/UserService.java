package com.epam.service;

import com.epam.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    User findById(String id);

    void deleteById(String id);

}
