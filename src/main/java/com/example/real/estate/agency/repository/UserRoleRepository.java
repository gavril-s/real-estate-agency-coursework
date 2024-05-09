package com.example.real.estate.agency.repository;

import com.example.real.estate.agency.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole getUserRoleById(Long userId);
}
