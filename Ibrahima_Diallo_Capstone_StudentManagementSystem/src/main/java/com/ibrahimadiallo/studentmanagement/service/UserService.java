package com.ibrahimadiallo.studentmanagement.service;

import com.ibrahimadiallo.studentmanagement.model.Role;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.repository.RoleRepository;
import com.ibrahimadiallo.studentmanagement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roleList = new ArrayList<>(user.getRoles());
        Role userRole = roleRepository.findByRole(roleList.get(0).getRole());
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
        return "success";
    }

    public List<User> findAllTeacher() {
        List<User> all = userRepository.findAll();
        List<User> filtered = new ArrayList<>();
        for (User user : all) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getRole().equalsIgnoreCase("Teacher")) {
                    filtered.add(user);
                    break;
                }
            }
        }
        return filtered;
    }

    public List<User> findAllStudent() {
        List<User> all = userRepository.findAll();
        List<User> filtered = new ArrayList<>();
        for (User user : all) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                if (role.getRole().equalsIgnoreCase("Student")) {
                    filtered.add(user);
                    break;
                }
            }
        }
        return filtered;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName).get();
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }
}
