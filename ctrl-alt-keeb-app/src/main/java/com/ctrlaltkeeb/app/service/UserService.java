package com.ctrlaltkeeb.app.service;

import com.ctrlaltkeeb.app.model.User;
import com.ctrlaltkeeb.app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  public User registerUser(String username, String email, String rawPassword, String role) {
    String encodedPassword = passwordEncoder.encode(rawPassword);

    String assignedRole = (role != null && !role.isEmpty()) ? role : "ROLE_CUSTOMER";

    User newUser = new User(username, email, encodedPassword, assignedRole);
    return userRepository.save(newUser);
  }
}
