package me.project.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.project.todo.model.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    // Account findByEmail(String email);
}

