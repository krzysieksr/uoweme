package com.dev.training.uownme.distribute.costs.controllers;

import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.distribute.costs.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistributeCostsController {
    private final UserRepository userRepository;

    public DistributeCostsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        final List<User> all = userRepository.findAll();
        return all;
    }
}
