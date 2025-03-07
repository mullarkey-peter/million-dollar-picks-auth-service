package com.glizzy.milliondollarpicks.authservice.repository;

import com.glizzy.milliondollarpicks.authservice.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByUsername(String username);
    Optional<Credentials> findByUserId(Long userId);
    boolean existsByUsername(String username);
}