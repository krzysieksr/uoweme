package com.dev.training.uownme.common.repositories;

import com.dev.training.uownme.common.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
