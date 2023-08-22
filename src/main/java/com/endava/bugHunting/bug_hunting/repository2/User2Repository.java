package com.endava.bugHunting.bug_hunting.repository2;

import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.entities2.User2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface User2Repository extends JpaRepository<User2, Long> {

    List<User2> findByEmail(String email);

    Optional<User2> findByEmailAndPassword(String email, String password);
}
