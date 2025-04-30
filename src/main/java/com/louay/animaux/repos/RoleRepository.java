package com.louay.animaux.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.louay.animaux.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
} 