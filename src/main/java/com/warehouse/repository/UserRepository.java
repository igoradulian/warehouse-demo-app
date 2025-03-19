package com.warehouse.repository;

import com.warehouse.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author igoradulyan on 12/18/24
 * @project IntelliJ IDEA
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    public User findUserByUserId(String userId);
}
