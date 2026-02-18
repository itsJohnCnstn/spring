package com.johncnstn.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<User, Long> {
    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);
}
