package com.endava.bugHunting.bug_hunting.repository;

import com.endava.bugHunting.bug_hunting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
