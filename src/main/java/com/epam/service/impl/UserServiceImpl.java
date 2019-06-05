package com.epam.service.impl;

import com.epam.dao.RoleRepository;
import com.epam.dao.UserRepository;
import com.epam.model.Role;
import com.epam.model.User;
import com.epam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username or email: " + usernameOrEmail));
    }

    @Override
    public User findById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public void deleteById(String id) {
        userRepository.delete(id);
    }

    @Override
    public User save(User user) {
        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() ->
                new RuntimeException("Role is not found!"));

        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

}
