package com.university.service;

import com.university.model.User;
import com.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        Optional<User> byUsernameAndPassword = userRepository.findByUsernameAndPassword(username, password);
        return byUsernameAndPassword.orElse(null);
    }

    @Transactional
    public String register(User user) {
        Optional<User> userDetailsOptional = userRepository.findByUsername(user.getUsername());
        if (userDetailsOptional.isPresent()) {
            return null;
        }
        userRepository.save(user);
        return "success";
    }

    public List<User> findAllTeacher() {
        return userRepository.findAllByRole("Teacher");
    }
    public List<User> findAllStudent() {
        return userRepository.findAllByRole("Student");
    }
}
