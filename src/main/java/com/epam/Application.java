package com.epam;

import com.epam.dao.RoleRepository;
import com.epam.model.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demoData(RoleRepository roleRepository) {
    return args -> {
      roleRepository.deleteAll();
      roleRepository.save(new Role("ROLE_USER"));
      roleRepository.save(new Role("ROLE_ADMIN"));
    };
  }

}
