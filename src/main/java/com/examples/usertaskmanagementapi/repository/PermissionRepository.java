package com.examples.usertaskmanagementapi.repository;

import com.examples.usertaskmanagementapi.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}