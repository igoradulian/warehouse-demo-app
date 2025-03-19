package com.warehouse.service;

import com.warehouse.model.entity.Roles;
import com.warehouse.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Service
public class RolesServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Roles findRoleByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }

    @Override
    public List<Roles> getRolesByUser(int id) {
        return null;
    }

    @Override
    public void saveRoles(Roles role) {
        roleRepository.save(role);
    }
}
