package com.project.p_auth.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByusername(String username);

    Optional<User> findByemail(String email);
    Page<User> findAll(Pageable pageable);
    Page<User> findByusernameContaining(String username ,Pageable pageable);
}
