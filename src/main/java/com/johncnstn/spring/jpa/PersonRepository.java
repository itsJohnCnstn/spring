package com.johncnstn.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
