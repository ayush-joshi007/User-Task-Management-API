package com.examples.usertaskmanagementapi.repository;

import com.examples.usertaskmanagementapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}