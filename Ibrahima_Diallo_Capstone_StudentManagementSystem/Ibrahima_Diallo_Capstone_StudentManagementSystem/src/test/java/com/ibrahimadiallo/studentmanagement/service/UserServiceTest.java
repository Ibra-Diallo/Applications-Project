package com.ibrahimadiallo.studentmanagement.service;

import com.ibrahimadiallo.studentmanagement.model.Role;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.repository.RoleRepository;
import com.ibrahimadiallo.studentmanagement.repository.UserRepository;
import com.ibrahimadiallo.studentmanagement.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private RoleRepository roleRepository;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, bCryptPasswordEncoder, roleRepository);
    }

    @Test
    public void testRegister() {
        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        User user = new User();
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
        roleSet.add(role);

        user.setRoles(roleSet);
        String result = userService.register(user);
        Assertions.assertEquals("success", result);
    }
}
