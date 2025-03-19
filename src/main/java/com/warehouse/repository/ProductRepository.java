package com.warehouse.repository;

import com.warehouse.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * @author igoradulyan on 12/16/24
 * @project IntelliJ IDEA
 */
public interface ProductRepository extends CrudRepository<Product,Long> {
    public Product findProductByName(String name);
    public Product findProductByProductId(String productId);
}
