package com.warehouse.service;

import com.warehouse.exceptions.UserAlreadyExistException;
import com.warehouse.model.dto.UserDTO;
import com.warehouse.model.entity.Roles;
import com.warehouse.model.entity.User;
import com.warehouse.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // The username parameter represents the login value from the form
        // which is mapped to the username column in the database. The
        // previous implementation searched by email causing authentication
        // failures when users attempted to log in with their usernames.
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid login or password");
        }
        return
                new org.springframework.security
                        .core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
        Collection<? extends GrantedAuthority> mapRoles = roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        return mapRoles;
    }
    public User findUserByUsername(String username) {
        return null;
    }

    public User findUserByEmail(String email) {
        return null;
    }

    public User findUserByUserId(String userId) {
        return null;
    }

    /**
     * This method saves a user to the database
     * We add here few functionalities
     * @param user
     * @return
     */
    @Override
    public UserDTO saveUser(UserDTO user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("User already exists");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User userEntity = modelMapper.map(user, User.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        Roles roles = roleService.findRoleByRole("ROLE_USER");
        ArrayList rolesList = new ArrayList<Roles>();
        rolesList.add(roles);
        userEntity.setRoles(rolesList);

        userRepository.save(userEntity);
        return user;
    }
}
