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
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(">>> DATALOADER RUNNING NOW <<<");

    User existingAdmin = userRepository.findByUsername("admin").orElse(null);

    if (existingAdmin == null) {
      User admin = new User(
          "admin",
          "admin@ctrlaltkeeb.com",
          passwordEncoder.encode("admin123"),
          "ROLE_ADMIN");

      userRepository.save(admin);
      System.out.println("--> Seeded default admin user: admin / admin123 meep meep");
    } else {
      System.out.println("--> Admin user already exists in DB!");
    }
  }
}
