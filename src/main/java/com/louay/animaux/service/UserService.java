package com.louay.animaux.service;

import com.louay.animaux.entities.Role;
import com.louay.animaux.entities.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserByUsername(String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    List<User> findAllUsers();
} 