package com.examples.usertaskmanagementapi.repository;

import com.examples.usertaskmanagementapi.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
}