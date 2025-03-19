package com.warehouse.service;

import com.warehouse.model.dto.UserDTO;
import com.warehouse.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
public interface UserService extends UserDetailsService {

    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    public User findUserByUserId(String userId);
    public UserDTO saveUser(UserDTO user);
}
