package com.warehouse.service;

import com.warehouse.model.entity.Roles;
import com.warehouse.repository.RoleRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RolesServiceTest {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleRepository roleRepository;

    Roles userRole;

    Roles adminRole;

    @BeforeAll
    public void setUp() {
        userRole = new Roles();
        userRole.setRole("ROLE_USER");

        adminRole = new Roles();
        adminRole.setRole("ROLE_ADMIN");
    }

    @Test
    public void saveRolesTest() {
        roleService.saveRoles(userRole);
        roleService.saveRoles(adminRole);
        long num = roleRepository.count();
        Assertions.assertTrue(num > 0);
    }

}
