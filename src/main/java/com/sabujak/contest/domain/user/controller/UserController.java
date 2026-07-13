package com.sabujak.contest.domain.user.controller;

import com.sabujak.contest.domain.user.entity.User;
import com.sabujak.contest.domain.user.repository.UserRepository;
import com.sabujak.contest.global.common.response.ResponseDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/health")
  public ResponseDTO<String> healthCheck() {

    return ResponseDTO.of("OK");

  }

//  @GetMapping
//  public List<User> findAll() {
//    return userRepository.findAll();
//  }
//
//  @GetMapping("/{id}")
//  public User findUserById(@PathVariable Long id) {
//    Optional<User> response = userRepository.findById(id);
//    return response
//        .orElseThrow(
//            () -> new RuntimeException("유저를 찾을 수 없다.")
//        );
//  }


}
