package com.epam.config;

import com.epam.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GlobalAuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final UserServiceImpl userDetailsService;

  @Override
  public void init(final AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }

}
