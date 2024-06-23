package com.crio.orbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.orbs.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
