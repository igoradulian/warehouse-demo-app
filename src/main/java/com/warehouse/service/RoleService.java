package com.warehouse.service;

import com.warehouse.model.entity.Roles;

import java.util.List;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
public interface RoleService {

    public Roles findRoleByRole(String role);
    public List<Roles> getRolesByUser(int id);
    public void saveRoles(Roles role);
}
