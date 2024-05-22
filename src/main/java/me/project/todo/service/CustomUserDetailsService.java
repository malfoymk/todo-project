package me.project.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import me.project.todo.model.Account;
import me.project.todo.repository.UserRepository;
import me.project.todo.util.OperationUser;
import me.project.todo.util.OperationUser.OperationType;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Deque<OperationUser> operationHistory = new LinkedList<>();
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public Account createdUser(Account newUser) {
        Account createdUser = userRepository.save(newUser);
        logger.info("Criando o usuário com o ID: {}", createdUser.getId());
        operationHistory.push(new OperationUser(OperationType.CREATE, createdUser));
        return createdUser;
    }

    public boolean existbyUsername(String username) {
        Account user = userRepository.findByUsername(username);
        if (user != null) {
            throw new UsernameNotFoundException("User already exists with username: " + username);
        }
        return false;
    }

    public boolean existByEmail(Object email) {
        throw new UnsupportedOperationException("Unimplemented method 'existByEmail'");
    }
}
