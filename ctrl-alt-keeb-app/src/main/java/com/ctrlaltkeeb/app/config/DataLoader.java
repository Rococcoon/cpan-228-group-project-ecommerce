package com.ctrlaltkeeb.app.config;

import com.ctrlaltkeeb.app.model.User;
import com.ctrlaltkeeb.app.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(ApplicationArguments args) {

    System.out.println(">>> DATALOADER RUNNING NOW <<<");

    // Create Admin account
    if (userRepository.findByUsername("admin").isEmpty()) {

      User admin = new User(
          "admin",
          "admin@ctrlaltkeeb.com",
          passwordEncoder.encode("admin123"),
          "ROLE_ADMIN");

      userRepository.save(admin);

      System.out.println("--> Created admin account");
    }

    // Create Staff account
    if (userRepository.findByUsername("staff").isEmpty()) {

      User staff = new User(
          "staff",
          "staff@ctrlaltkeeb.com",
          passwordEncoder.encode("staff123"),
          "ROLE_STAFF");

      userRepository.save(staff);

      System.out.println("--> Created staff account");
    }

    System.out.println(">>> DATALOADER COMPLETE <<<");
  }
}