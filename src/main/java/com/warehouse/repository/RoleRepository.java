package com.warehouse.repository;

import com.warehouse.model.entity.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Repository
public interface RoleRepository extends CrudRepository<Roles,Long> {
    public Roles findRoleByRole(String role);
}
