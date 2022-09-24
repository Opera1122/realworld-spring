package com.realworld.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Taewoo
 */


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);

}
