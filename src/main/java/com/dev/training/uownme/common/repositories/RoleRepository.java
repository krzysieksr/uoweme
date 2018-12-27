package com.dev.training.uownme.common.repositories;

import com.dev.training.uownme.common.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
